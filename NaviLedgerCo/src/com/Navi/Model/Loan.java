package com.Navi.Model;

import java.util.List;

public class Loan {
    private String bankName;
    private Double amount;
    private Double principle;
    private Double rate;
    private Integer years;
    private Integer lastEmi;
    private List<Emi> emis;

    public Loan() {
    }

    public Loan(String bankName, Double principle, Double rate, Integer years) {
        this.bankName = bankName;
        this.principle = principle;
        this.rate = rate;
        this.years = years;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrinciple() {
        return principle;
    }

    public void setPrinciple(Double principle) {
        this.principle = principle;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public List<Emi> getEmis() {
        return emis;
    }

    public void setEmis(List<Emi> emis) {
        this.emis = emis;
    }

    public Integer getLastEmi() {
        return lastEmi;
    }

    public void setLastEmi(Integer lastEmi) {
        this.lastEmi = lastEmi;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "bankName='" + bankName + '\'' +
                ", amount=" + amount +
                ", principle=" + principle +
                ", rate=" + rate +
                ", years=" + years +
                ", lastEmi=" + lastEmi +
                ", emis=" + emis.toString() +
                '}';
    }
}
