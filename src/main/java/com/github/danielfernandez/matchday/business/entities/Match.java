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

@Document(collection = "matches")
@TypeAlias("match")
public class Match {

    // An alternative approach here would be using @DBRef's, but besides
    // not being something fully recommended in MongoDB data design,
    // the MongoDB ReactiveStreams driver does not support them:
    // see https://jira.spring.io/browse/DATAMONGO-1584

    @Id private String id = null;
    private String teamACode = null;
    private String teamBCode = null;



    public Match() {
        super();
    }

    public Match(final String teamACode, final String teamBCode) {
        super();
        this.teamACode = teamACode;
        this.teamBCode = teamBCode;
    }


    public String getId() {
        return this.id;
    }

    public String getTeamACode() {
        return this.teamACode;
    }

    public void setTeamACode(final String teamACode) {
        this.teamACode = teamACode;
    }

    public String getTeamBCode() {
        return this.teamBCode;
    }

    public void setTeamBCode(final String teamBCode) {
        this.teamBCode = teamBCode;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id='" + this.id + '\'' +
                ", teamACode='" + this.teamACode + '\'' +
                ", teamBCode='" + this.teamBCode + '\'' +
                '}';
    }

}
