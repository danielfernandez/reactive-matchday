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
package com.github.danielfernandez.matchday.business.dataviews;


import com.github.danielfernandez.matchday.business.entities.Team;

public class MatchInfo {

    private final String matchId;
    private final Team teamA;
    private final Team teamB;


    public MatchInfo(final String matchId, final Team teamA, final Team teamB) {
        super();
        this.matchId = matchId;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }


    @Override
    public String toString() {
        return "MatchInfo{" +
                "matchId='" + this.matchId + '\'' +
                ", teamA=" + this.teamA +
                ", teamB=" + this.teamB +
                '}';
    }

}
