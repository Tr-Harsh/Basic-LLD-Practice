package com.CRED.Strategies;

public class Strategy {
    private Integer bonusValue;

    public Strategy() {
        bonusValue = 0;
    }

    public Strategy(Integer bonusValue) {
        this.bonusValue = bonusValue;
    }

    public Integer getBonusValue() {
        return bonusValue;
    }
}
