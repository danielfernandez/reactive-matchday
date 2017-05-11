package com.github.danielfernandez.matchday.business;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchSummary {

    private String matchId;
    private final Team teamA;
    private final Team teamB;
    private final int scoreA;
    private final int scoreB;
    private final List<MatchEvent> events;


    public MatchSummary(
            final String matchId,
            final Team teamA, final Team teamB,
            final int scoreA, final int scoreB,
            final List<MatchEvent> events) {
        super();
        this.matchId = matchId;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.events = Collections.unmodifiableList(new ArrayList<>(events));
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

    public int getScoreA() {
        return this.scoreA;
    }

    public int getScoreB() {
        return this.scoreB;
    }

    public List<MatchEvent> getEvents() {
        return this.events;
    }


    @Override
    public String toString() {
        return "MatchSummary{" +
                "matchId='" + this.matchId + '\'' +
                ", teamA=" + this.teamA +
                ", teamB=" + this.teamB +
                ", scoreA=" + this.scoreA +
                ", scoreB=" + this.scoreB +
                ", events=" + this.events +
                '}';
    }

}
