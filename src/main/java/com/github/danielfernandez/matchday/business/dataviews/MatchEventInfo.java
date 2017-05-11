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


import com.github.danielfernandez.matchday.business.entities.MatchEvent;

public class MatchEventInfo implements Comparable<MatchEventInfo> {

    private final MatchEvent.Type type;
    private final PlayerInfo playerInfo;
    private final String timestamp;


    public MatchEventInfo(final MatchEvent.Type type, final PlayerInfo playerInfo, final String timestamp) {
        super();
        this.type = type;
        this.playerInfo = playerInfo;
        this.timestamp = timestamp;
    }

    public MatchEvent.Type getType() {
        return type;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public String getPlayerName() {
        return getPlayerInfo().getName();
    }

    public String getTeamCode() {
        return getPlayerInfo().getTeam().getCode();
    }

    public String getTeamName() {
        return getPlayerInfo().getTeamName();
    }

    public String getTimestamp() {
        return this.timestamp;
    }



    @Override
    public int compareTo(final MatchEventInfo o) {
        return -1 * this.timestamp.compareTo(o.timestamp);
    }


    @Override
    public String toString() {
        return "MatchEventInfo{" +
                "type=" + this.type +
                ", playerInfo=" + this.playerInfo +
                '}';
    }

}
