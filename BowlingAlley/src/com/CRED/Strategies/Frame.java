package com.CRED.Strategies;

public class Frame {
    private Integer frameScore;

    public Frame(Integer frameScore) {
        this.frameScore = frameScore;
    }

    public Frame() {
        frameScore = 0;
    }

    public Integer getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(Integer frameScore) {
        this.frameScore = frameScore;
    }
}
