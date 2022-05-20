package com.Navi.Model;

import java.util.List;

public class Person {
    private String name;
    private List<Loan> loans;

    public Person() {
    }

    public Person(String name, List<Loan> loans) {
        this.name = name;
        this.loans = loans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", loans=" + loans.toString() +
                '}';
    }
}
