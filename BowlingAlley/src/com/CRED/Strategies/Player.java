package com.CRED.Strategies;

import com.CRED.Window;

import java.util.List;

public class Player {
    private Integer id;
    private String name;
    private List<Window> windows;
    private Integer totalScore;

    public Player(Integer id, String name, List<Window> windows, Integer totalScore) {
        this.id = id;
        this.name = name;
        this.windows = windows;
        this.totalScore = totalScore;
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
