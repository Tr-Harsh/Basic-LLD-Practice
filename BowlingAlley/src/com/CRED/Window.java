package com.CRED;

import com.CRED.Strategies.Frame;
import com.CRED.Strategies.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Window {
    private List<Frame> frames;
    private Strategy strategy;
    private Integer totalScore;

    public Window() {
        frames = new ArrayList<>();
        totalScore = 0;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
