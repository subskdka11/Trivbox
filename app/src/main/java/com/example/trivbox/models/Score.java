package com.example.trivbox.models;

import java.io.Serializable;

public class Score implements Serializable {
    private int _id;
    private String category, difficulty, type, point;

    public Score() {}

    public Score(String category, String difficulty, String type, String point) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.point = point;
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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
