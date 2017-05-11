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
package com.github.danielfernandez.matchday.business.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
@TypeAlias("player")
public class Player {

    // An alternative approach here would be using @DBRef's, but besides
    // not being something fully recommended in MongoDB data design,
    // the MongoDB ReactiveStreams driver does not support them:
    // see https://jira.spring.io/browse/DATAMONGO-1584

    @Id private String id = null;
    private String teamCode = null;
    private String name = null;



    public Player() {
        super();
    }

    public Player(final String teamCode, final String name) {
        super();
        this.teamCode = teamCode;
        this.name = name;
    }


    public String getId() {
        return this.id;
    }

    public String getTeamCode() {
        return this.teamCode;
    }

    public void setTeamCode(final String teamCode) {
        this.teamCode = teamCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id='" + this.id + '\'' +
                ", teamCode='" + this.teamCode + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }

}
