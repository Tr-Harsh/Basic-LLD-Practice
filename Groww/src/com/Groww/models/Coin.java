package com.Groww.models;

public enum Coin {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDERED(100),
    TWO_HUNDERED(200),
    FIVE_HUNDERED(500),
    TWO_THOUSAND(2000);

    private int denomination;

    private Coin(int denomination){
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
