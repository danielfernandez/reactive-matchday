package com.github.danielfernandez.matchday.business;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matchevents")
@TypeAlias("matchevent")
public class MatchEvent {

    public enum Type { GOAL, YELLOW_CARD, RED_CARD }


    // An alternative approach here would be using @DBRef's, but besides
    // not being something fully recommended in MongoDB data design,
    // the MongoDB ReactiveStreams driver does not support them:
    // see https://jira.spring.io/browse/DATAMONGO-1584

    @Id private String id = null;
    private String matchId = null;
    private Type type = null;
    private String teamCode = null;
    private String playerId = null;



    public MatchEvent(final String matchId, final Type type, final String teamCode, final String playerId) {
        super();
        this.matchId = matchId;
        this.type = type;
        this.teamCode = teamCode;
        this.playerId = playerId;
    }


    public String getId() {
        return this.id;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public void setMatchId(final String matchId) {
        this.matchId = matchId;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getTeamCode() {
        return this.teamCode;
    }

    public void setTeamCode(final String teamCode) {
        this.teamCode = teamCode;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }


    @Override
    public String toString() {
        return "MatchEvent{" +
                "id='" + this.id + '\'' +
                ", matchId='" + this.matchId + '\'' +
                ", type=" + this.type +
                ", teamCode='" + this.teamCode + '\'' +
                ", playerId='" + this.playerId + '\'' +
                '}';
    }
}
