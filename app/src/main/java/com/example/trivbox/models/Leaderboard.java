package com.example.trivbox.models;

public class Leaderboard {
    private String leaderboardID;
    private Score score;
    private String name;

    public Leaderboard() {}

    public Leaderboard(String leaderboardID, Score score, String name) {
        this.leaderboardID = leaderboardID;
        this.score = score;
        this.name = name;
    }

    public String getLeaderboardID() {
        return leaderboardID;
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
