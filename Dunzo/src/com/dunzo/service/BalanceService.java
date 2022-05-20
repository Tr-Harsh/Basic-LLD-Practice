package com.dunzo.service;

import com.dunzo.model.Balance;
import com.dunzo.model.Expense;

import java.util.*;

public class BalanceService {

    Map<String, Double> balanceMap;
    PriorityQueue<Balance> minQueue = new PriorityQueue<>(new MinComparator());
    PriorityQueue<Balance> maxQueue = new PriorityQueue<>(new MaxComparator());

    public void setBalanceMap(Map<String, Double> balanceMap) {
        this.balanceMap = balanceMap;
    }

    public BalanceService(Map<String, Double> balanceMap) {
        this.balanceMap = balanceMap;
    }

    public Map<String, Double> buildBalanceGraph(List<Expense> expenses){
        for(Expense expense : expenses){
            balanceMap.put(expense.getUser1(), balanceMap.getOrDefault(expense.getUser1(),0.0)-expense.getAmount());
        }

        for(Expense expense : expenses){
            balanceMap.put(expense.getUser2(), balanceMap.getOrDefault(expense.getUser2(),0.0)+expense.getAmount());
        }
        return balanceMap;
    }

    public void reset(){
        balanceMap = new HashMap<>();
        minQueue = new PriorityQueue<>(new MinComparator());
        maxQueue = new PriorityQueue<>(new MaxComparator());
    }

    public void printSimplificationBalance(List<Expense> expenses){
        balanceMap.forEach((k,v)->{
            System.out.println("User "+k+" owes in total "+ v);
            if(v<0) {
                minQueue.add(new Balance(k, v));
            }
            else {
                maxQueue.add(new Balance(k,v));
            }
        });

        System.out.println("Printing simplification balance");
        while(!maxQueue.isEmpty()){

            Balance largestAmountUser = maxQueue.poll();
            Balance smallestAmountUser = minQueue.poll();
            double largestAmount = largestAmountUser.getAmount();
            double smallestAmount = -smallestAmountUser.getAmount();

            double minimumAmount = Math.min(largestAmount, smallestAmount);
            balanceMap.put(largestAmountUser.getUserId(),balanceMap.getOrDefault(largestAmountUser.getUserId(),0.0)-minimumAmount);
            balanceMap.put(smallestAmountUser.getUserId(),balanceMap.getOrDefault(smallestAmountUser.getUserId(),0.0)+minimumAmount);

            double remaining = largestAmount - smallestAmount;
            System.out.println("User "+smallestAmountUser.getUserId()
                    +" owes User "+ largestAmountUser.getUserId()
                    +" " + minimumAmount);

            if (remaining > 0) {
                maxQueue.add(new Balance(largestAmountUser.getUserId(), remaining));
            } else if (remaining < 0) {
                minQueue.add(new Balance(smallestAmountUser.getUserId(), remaining));
            }

        }
    }

    public void printWithoutSimplificationBalance(List<Expense> expenses){
        balanceMap.forEach((k,v)-> System.out.println("User "+k+" owes in total "+ v));

        System.out.println("Printing without simplification balance");
        expenses.forEach(ex->{
            System.out.println("User "+ex.getUser1()
                    +" owes User "+ ex.getUser2()
                    + " " + ex.getAmount());
        });

        System.out.println();
        System.out.println();
    }

    static class MinComparator implements Comparator<Balance>{

        @Override
        public int compare(Balance o1, Balance o2) {
            if(o1.getAmount()<o2.getAmount()) return -1;
            else if(o1.getAmount()>o2.getAmount()) return 1;
            else return 0;
        }
    }

    static class MaxComparator implements Comparator<Balance>{

        @Override
        public int compare(Balance o1, Balance o2) {
            if(o1.getAmount()>o2.getAmount()) return -1;
            else if(o1.getAmount()<o2.getAmount()) return 1;
            else return 0;
        }
    }
}
