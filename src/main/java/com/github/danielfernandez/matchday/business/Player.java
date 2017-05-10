package com.github.danielfernandez.matchday.business;


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
