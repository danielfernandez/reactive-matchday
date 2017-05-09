package com.danielfernandez.basketflux.business;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matchcomments")
public class MatchComment {


    private final @Id String id;
    private final String matchId;
    private final String comment;


    public MatchComment(final String id, final String matchId, final String comment) {
        super();
        this.id = id;
        this.matchId = matchId;
        this.comment = comment;
    }


    public String getId() {
        return this.id;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public String toString() {
        return "MatchComment{" +
                "id='" + id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}
