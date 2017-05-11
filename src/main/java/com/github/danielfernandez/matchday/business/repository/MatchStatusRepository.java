/*
 * =============================================================================
 *
 *   Copyright (c) 2017, Daniel Fernandez (http://github.com/danielfernandez)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package com.github.danielfernandez.matchday.business.repository;


import java.util.ArrayList;
import java.util.List;

import com.github.danielfernandez.matchday.business.dataviews.MatchEventInfo;
import com.github.danielfernandez.matchday.business.dataviews.MatchInfo;
import com.github.danielfernandez.matchday.business.dataviews.MatchStatus;
import com.github.danielfernandez.matchday.business.entities.MatchEvent;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MatchStatusRepository {


    private final MatchInfoRepository matchInfoRepository;
    private final MatchEventInfoRepository matchEventInfoRepository;


    public MatchStatusRepository(
            final MatchInfoRepository matchInfoRepository,
            final MatchEventInfoRepository matchEventInfoRepository) {
        super();
        this.matchInfoRepository = matchInfoRepository;
        this.matchEventInfoRepository = matchEventInfoRepository;
    }



    public Flux<MatchStatus> findAllMatchStatus() {

        final Flux<MatchInfo> matchInfo = this.matchInfoRepository.findAllMatchInfo();
        return matchInfo
                .map(MatchEventAggregator::new)
                .concatMap(
                        agg ->
                                this.matchEventInfoRepository
                                        .findAllMatchEventInfoByMatchId(agg.getMatchId())
                                        .collectList()
                                        .map(agg::withAllEvents));

    }



    public Flux<MatchStatus> tailMatchStatusStreamByMatchId(final String matchId) {

        /*
         * This is TAILABLE, i.e. it will use a MongoDB "tailable cursor" on the "matchevents"
         * collection, which is a capped collection.
         *
         * See: https://docs.mongodb.com/manual/core/tailable-cursors/
         */

        final Mono<MatchInfo> matchInfo = this.matchInfoRepository.getMatchInfo(matchId);
        return matchInfo
                .map(MatchEventAggregator::new)
                .flatMapMany(
                        agg ->
                                this.matchEventInfoRepository
                                        .tailAllMatchEventInfoByMatchId(agg.getMatchId())
                                        .map(agg::withEvent));

    }





    static class MatchEventAggregator {

        private final MatchInfo matchInfo;
        private final String teamACode;
        private final String teamBCode;
        private int scoreA;
        private int scoreB;
        private final List<MatchEventInfo> events;


        MatchEventAggregator(final MatchInfo matchInfo) {
            super();
            this.matchInfo = matchInfo;
            this.teamACode = this.matchInfo.getTeamA().getCode();
            this.teamBCode = this.matchInfo.getTeamB().getCode();
            this.scoreA = 0;
            this.scoreB = 0;
            this.events = new ArrayList<>();
        }

        String getMatchId() {
            return this.matchInfo.getMatchId();
        }

        private void addEvent(final MatchEventInfo event) {

            // We don't want MATCH_START events to appear on the list
            if (event.getType() == MatchEvent.Type.MATCH_START) {
                return;
            }

            // If event is a GOAL, we will first increment the corresponding score
            if (event.getType() == MatchEvent.Type.GOAL) {
                final String eventTeamCode =
                        event.getPlayerInfo().getTeam().getCode();
                if (eventTeamCode.equals(this.teamACode)) {
                    this.scoreA++;
                } else if (eventTeamCode.equals(this.teamBCode)) {
                    this.scoreB++;
                }
            }

            this.events.add(event);

        }


        MatchStatus withAllEvents(final List<MatchEventInfo> events) {
            events.forEach(this::addEvent);
            return new MatchStatus(
                    this.matchInfo, this.scoreA, this.scoreB, this.events);
        }


        MatchStatus withEvent(final MatchEventInfo event) {
            addEvent(event);
            return new MatchStatus(
                    this.matchInfo, this.scoreA, this.scoreB, this.events);
        }

    }

}
