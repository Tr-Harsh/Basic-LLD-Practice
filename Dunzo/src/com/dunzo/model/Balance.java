package com.dunzo.model;

public class Balance {
    private String userId;
    private double amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Balance(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
