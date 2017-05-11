package com.github.danielfernandez.matchday.business.repository;


import java.util.ArrayList;
import java.util.List;

import com.github.danielfernandez.matchday.business.Match;
import com.github.danielfernandez.matchday.business.MatchEvent;
import com.github.danielfernandez.matchday.business.MatchSummary;
import com.github.danielfernandez.matchday.business.Team;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MatchSummaryRepository {


    private final ReactiveMongoTemplate mongoTemplate;


    public MatchSummaryRepository(final ReactiveMongoTemplate mongoTemplate) {
        super();
        this.mongoTemplate = mongoTemplate;
    }



    public Flux<MatchSummary> tailMatchSummary(final String matchId) {

        final Mono<MatchEventAggregator> matchAgg =
                createAggregator(this.mongoTemplate.findById(matchId, Match.class));

        // TODO * Order events by timestamp desc (need to add timestamp)
        final Query matchEventQuery = Query.query(Criteria.where("matchId").is(matchId));

        return matchAgg.flatMapMany(
                m -> this.mongoTemplate.tail(matchEventQuery, MatchEvent.class)
                            .map(event -> {
                                    m.addEvent(event);
                                    return m.produceMatchSummary();
                                }));

    }



    private Mono<MatchEventAggregator> createAggregator(final Mono<Match> match) {
        return match.flatMap(
                m -> this.mongoTemplate.findById(m.getTeamACode(), Team.class)
                            .and(this.mongoTemplate.findById(m.getTeamBCode(), Team.class))
                            .map(teams -> new MatchEventAggregator(m.getId(), teams.getT1(), teams.getT2())));
    }




    static class MatchEventAggregator {

        private final String matchId;
        private final Team teamA;
        private final Team teamB;
        private int scoreA;
        private int scoreB;
        private final List<MatchEvent> events;


        MatchEventAggregator(final String matchId, final Team teamA, final Team teamB) {
            super();
            this.matchId = matchId;
            this.teamA = teamA;
            this.teamB = teamB;
            this.scoreA = 0;
            this.scoreB = 0;
            this.events = new ArrayList<>();
        }


        void addEvent(final MatchEvent event) {
            // If event is a GOAL, we will first increment the corresponding score
            if (event.getType() == MatchEvent.Type.GOAL) {
                if (this.teamA.getCode().equals(event.getTeamCode())) {
                    this.scoreA++;
                } else if (this.teamB.getCode().equals(event.getTeamCode())) {
                    this.scoreB++;
                }
            }
            this.events.add(event);
        }


        public MatchSummary produceMatchSummary() {
            return new MatchSummary(
                    this.matchId, this.teamA, this.teamB,
                    this.scoreA, this.scoreB, this.events);
        }

    }

}
