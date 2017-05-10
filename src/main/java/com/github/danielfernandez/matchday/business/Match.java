package com.github.danielfernandez.matchday.business;


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
