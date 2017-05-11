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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.danielfernandez.matchday.business.entities.Team;

public class MatchStatus {

    private final MatchInfo matchInfo;
    private final int teamAScore;
    private final int teamBScore;
    private final List<MatchEventInfo> events;


    public MatchStatus(
            final MatchInfo matchInfo,
            final int teamAScore, final int teamBScore,
            final List<MatchEventInfo> events) {
        super();
        this.matchInfo = matchInfo;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        final List<MatchEventInfo> statusEvents = new ArrayList<>(events);
        Collections.sort(statusEvents);
        this.events = Collections.unmodifiableList(statusEvents);
    }

    public String getMatchId() {
        return this.matchInfo.getMatchId();
    }

    public Team getTeamA() {
        return this.matchInfo.getTeamA();
    }

    public Team getTeamB() {
        return this.matchInfo.getTeamB();
    }

    public String getTeamACode() {
        return getTeamA().getCode();
    }

    public String getTeamBCode() {
        return getTeamB().getCode();
    }

    public String getTeamAName() {
        return getTeamA().getName();
    }

    public String getTeamBName() {
        return getTeamB().getName();
    }

    public int getTeamAScore() {
        return this.teamAScore;
    }

    public int getTeamBScore() {
        return this.teamBScore;
    }

    public List<MatchEventInfo> getEvents() {
        return this.events;
    }


    @Override
    public String toString() {
        return "MatchStatus{" +
                "matchInfo='" + this.matchInfo + '\'' +
                ", teamAScore=" + this.teamAScore +
                ", teamBScore=" + this.teamBScore +
                ", events=" + this.events +
                '}';
    }

}
