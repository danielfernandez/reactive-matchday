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


import com.github.danielfernandez.matchday.business.dataviews.MatchInfo;
import com.github.danielfernandez.matchday.business.entities.Match;
import com.github.danielfernandez.matchday.business.entities.Team;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MatchInfoRepository {


    private final ReactiveMongoTemplate mongoTemplate;


    public MatchInfoRepository(final ReactiveMongoTemplate mongoTemplate) {
        super();
        this.mongoTemplate = mongoTemplate;
    }



    public Mono<MatchInfo> getMatchInfo(final String matchId) {
        return this.mongoTemplate.findById(matchId, Match.class).flatMap(this::buildMatchInfo);
    }


    public Flux<MatchInfo> findAllMatchInfo() {
        return this.mongoTemplate.findAll(Match.class).concatMap(this::buildMatchInfo);
    }


    private Mono<MatchInfo> buildMatchInfo(final Match match) {
        // For a specific Match, gets the info of the playing teams and creates the MatchInfo
        return this.mongoTemplate.findById(match.getTeamACode(), Team.class)
                .and(this.mongoTemplate.findById(match.getTeamBCode(), Team.class))
                .map(teams -> new MatchInfo(match.getId(), teams.getT1(), teams.getT2()));
    }

}
