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


import com.github.danielfernandez.matchday.util.TimestampUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matchcomments")
@TypeAlias("matchcomment")
public class MatchComment {

    // An alternative approach here would be using @DBRef's, but besides
    // not being something fully recommended in MongoDB data design,
    // the MongoDB ReactiveStreams driver does not support them:
    // see https://jira.spring.io/browse/DATAMONGO-1584

    @Id
    private String id = null;
    private String matchId = null;
    private String author = null;
    private String text = null;
    private String timestamp = null;


    public MatchComment() {
        super();
    }

    public MatchComment(final String matchId, final String author, final String text) {
        super();
        this.matchId = matchId;
        this.author = author;
        this.text = text;
        this.timestamp = TimestampUtils.computeISO8601Timestamp();
    }


    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public void setMatchId(final String matchId) {
        this.matchId = matchId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }



    @Override
    public String toString() {
        return "MatchComment{" +
                "id='" + this.id + '\'' +
                ", matchId='" + this.matchId + '\'' +
                ", author='" + this.author + '\'' +
                ", text='" + this.text + '\'' +
                ", timestamp='" + this.timestamp + '\'' +
                '}';
    }

}
