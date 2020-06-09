package com.example.trivbox.models;

import java.io.Serializable;

public class Score implements Serializable {
    private int _id;
    private String category, difficulty, type, score;

    public Score(String category, String difficulty, String type, String score) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.score = score;
    }

    public Score(String category, String difficulty, String type) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
