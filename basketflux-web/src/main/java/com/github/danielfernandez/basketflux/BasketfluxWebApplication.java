package com.github.danielfernandez.basketflux;

import java.util.Collections;
import java.util.logging.Level;

import com.github.danielfernandez.basketflux.business.Match;
import com.github.danielfernandez.basketflux.business.Player;
import com.github.danielfernandez.basketflux.business.Team;
import com.github.danielfernandez.basketflux.util.Data;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@SpringBootApplication
public class BasketfluxWebApplication {

	private static final String LOGGER_INITIALIZE = BasketfluxWebApplication.class.getName() + ".DATA_INIT";


	@Bean
	public ApplicationRunner initialize(final ReactiveMongoTemplate mongoTemplate) {
		return args -> {

			/*
			 *  Drop collections, then create them again
			 */
			final Mono<Void> initializeCollections =
					mongoTemplate
							.dropCollection(Team.class)
							.then(mongoTemplate.dropCollection(Match.class))
							.then(mongoTemplate.dropCollection(Player.class))
							.then(mongoTemplate.createCollection(Team.class))
							.then(mongoTemplate.createCollection(Match.class))
							.then(mongoTemplate.createCollection(Player.class))
							.then();

			/*
			 * Add some test data to the collections: teams and players will come from the
			 * utility Data class, but we will generate matches between teams randomly each
			 * time the application starts (for the fun of it)
			 */
			final Mono<Void> initializeData =
					mongoTemplate
							// Insert all the teams into the corresponding collection and log
							.insert(Data.TEAMS, Team.class)
							.log(LOGGER_INITIALIZE, Level.FINEST)
							// Collect all inserted team codes and randomly shuffle the list
							.map(Team::getCode).collectList().doOnNext(Collections::shuffle)
							.flatMapMany(list -> Flux.fromIterable(list))
							// Create groups of two teams and insert a new Match for them
							.buffer(2).map(twoTeams -> new Match(twoTeams.get(0), twoTeams.get(1)))
							.flatMap(mongoTemplate::insert)
							.log(LOGGER_INITIALIZE, Level.FINEST)
							// Finally insert the players into their corresponding collection
							.thenMany(Flux.fromIterable(Data.PLAYERS))
							.flatMap(mongoTemplate::insert)
							.log(LOGGER_INITIALIZE, Level.FINEST)
							.then();


			initializeCollections.then(initializeData).block();


			findPlayersForMatch(
					mongoTemplate,
					mongoTemplate.findAll(Match.class)
						.take(1)
						.log(LOGGER_INITIALIZE, Level.FINEST).single())
					.log(LOGGER_INITIALIZE, Level.FINEST)
					.then().block();


		};
	}


	private Flux<Player> findPlayersForMatch(final ReactiveMongoTemplate mongoTemplate, final Mono<Match> match) {
		return match.flatMapMany(m -> {
			final Query query =
					Query.query(
							new Criteria().orOperator(
									Criteria.where("teamCode").is(m.getTeamACode()),
									Criteria.where("teamCode").is(m.getTeamBCode())));
			return mongoTemplate.find(query, Player.class);
		});
	}


	public static void main(String[] args) {
		SpringApplication.run(BasketfluxWebApplication.class, args);
	}

}
