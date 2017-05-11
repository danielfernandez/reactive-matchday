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


import com.github.danielfernandez.matchday.business.dataviews.PlayerInfo;
import com.github.danielfernandez.matchday.business.entities.Player;
import com.github.danielfernandez.matchday.business.entities.Team;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PlayerInfoRepository {


    private final ReactiveMongoTemplate mongoTemplate;


    public PlayerInfoRepository(final ReactiveMongoTemplate mongoTemplate) {
        super();
        this.mongoTemplate = mongoTemplate;
    }



    public Mono<PlayerInfo> getPlayerInfo(final String playerId) {
        return this.mongoTemplate.findById(playerId, Player.class).flatMap(this::buildPlayerInfo);
    }



    private Mono<PlayerInfo> buildPlayerInfo(final Player player) {
        // For a specific Player, gets the info of the associated team
        return this.mongoTemplate.findById(player.getTeamCode(), Team.class)
                .map(team -> new PlayerInfo(player.getId(), team, player.getName()));
    }

}
