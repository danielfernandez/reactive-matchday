package com.github.danielfernandez.matchday.agents;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import com.github.danielfernandez.matchday.business.Match;
import com.github.danielfernandez.matchday.business.MatchEvent;
import com.github.danielfernandez.matchday.business.Player;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class MatchEventAgent {

	private static final String LOGGER_AGENT = MatchEventAgent.class.getName() + ".EVENTS";

	private static final List<MatchEvent.Type> EVENT_TYPES_BY_PROBABILITY =
			Arrays.asList(
					// Probability of event being a GOAL: 45%
					MatchEvent.Type.GOAL, MatchEvent.Type.GOAL, MatchEvent.Type.GOAL, MatchEvent.Type.GOAL,
					MatchEvent.Type.GOAL, MatchEvent.Type.GOAL, MatchEvent.Type.GOAL, MatchEvent.Type.GOAL,
					// Probability of event being a YELLOW CARD: 50%
					MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD,
					MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD,
					MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD,
					MatchEvent.Type.YELLOW_CARD, MatchEvent.Type.YELLOW_CARD,
					// Probability of event being a RED CARD: 5%
					MatchEvent.Type.RED_CARD
			);


	private final ReactiveMongoTemplate mongoTemplate;
	private final Random random;





	public MatchEventAgent(final ReactiveMongoTemplate mongoTemplate) {
		super();
		this.random = new Random(System.currentTimeMillis());
		this.mongoTemplate = mongoTemplate;
	}




	public Flux<MatchEvent> createAgentStream() {
		final Flux<Integer> delayedStream = createDelayedStream();
		return delayedStream.concatMap(delay -> insertNewEvent());
	}



	private Flux<Integer> createDelayedStream() {
		// Will create a stream of signals delayed a random amoung of seconds (between 1 and 5)
		return Flux.generate((emitter) -> emitter.next(new Integer(this.random.nextInt(5 + 1))))
				.cast(Integer.class).concatMap(delay -> Mono.just(delay).delayElement(Duration.ofSeconds(delay)));
	}


	private Mono<MatchEvent> insertNewEvent() {
		// Will insert a new event for a random match and player (of that match)
		final Mono<Match> match = chooseMatch();
		final Mono<MatchEvent> event = createEvent(match);
		return event
				.flatMap(this.mongoTemplate::insert)
				.log(LOGGER_AGENT, Level.FINEST);
	}



	private Mono<Match> chooseMatch() {
		// Will randomly select a match from the ones on the database
		return this.mongoTemplate.findAll(Match.class).collectList().map(this::randomFromlist);
	}


	private Mono<MatchEvent> createEvent(final Mono<Match> match) {
		// Will create a match event of a random type for the selected match (and a random player)
		return match
				.flatMap(m -> {

						final Query query =
								Query.query(
										new Criteria().orOperator(
												Criteria.where("teamCode").is(m.getTeamACode()),
												Criteria.where("teamCode").is(m.getTeamBCode())));

						final Flux<Player> playersForMatch =
								this.mongoTemplate.find(query, Player.class);

						final Mono<Player> chosenPlayer =
								playersForMatch.collectList().map(this::randomFromlist);

						return chosenPlayer.map(
								p -> new MatchEvent(m.getId(), randomMatchType(), p.getTeamCode(), p.getId()));

					});
	}


	private <T> T randomFromlist(final List<T> list) {
		return list.get(this.random.nextInt(list.size()));
	}


	private MatchEvent.Type randomMatchType() {
		return randomFromlist(EVENT_TYPES_BY_PROBABILITY);
	}


}
