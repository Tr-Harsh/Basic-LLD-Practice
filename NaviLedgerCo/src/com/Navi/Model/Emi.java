package com.Navi.Model;

public class Emi {
    private Integer number;
    private Integer emiAmount;
    private double lumpSumAmount;
    private boolean lastEmi;

    public Emi() {
    }

    public Emi(Integer number, Integer emiAmount, boolean lastEmi) {
        this.number = number;
        this.emiAmount = emiAmount;
        this.lastEmi = lastEmi;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Integer emiAmount) {
        this.emiAmount = emiAmount;
    }

    public double getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(double lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public boolean isLastEmi() {
        return lastEmi;
    }

    public void setLastEmi(boolean lastEmi) {
        this.lastEmi = lastEmi;
    }

    @Override
    public String toString() {
        return "Emi{" +
                "number=" + number +
                ", emiAmount=" + emiAmount +
                ", lumpSumAmount=" + lumpSumAmount +
                ", lastEmi=" + lastEmi +
                '}';
    }
}
