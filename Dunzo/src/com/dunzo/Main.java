package com.dunzo;

import com.dunzo.model.Expense;
import com.dunzo.service.BalanceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// To run the application, run the main method of Main Class.
public class Main {

    private static BalanceService balanceService;
    public static void main(String[] args) {
        Expense e1 = new Expense("M", "N", 20);
        Expense e2 = new Expense("L", "M", 70);
        Expense e3 = new Expense("N", "L", 40);
        Expense e4 = new Expense("N", "K", 100);

        List<Expense> expenses = new ArrayList<>();
        expenses.add(e1);expenses.add(e2);expenses.add(e3);expenses.add(e4);

        balanceService = new BalanceService(new HashMap<>());
        Map<String, Double> balanceGraph = balanceService.buildBalanceGraph(expenses);
        balanceService.setBalanceMap(balanceGraph);
        balanceService.printWithoutSimplificationBalance(expenses);

        balanceService.reset();
        balanceGraph = balanceService.buildBalanceGraph(expenses);
        balanceService.setBalanceMap(balanceGraph);
        balanceService.printSimplificationBalance(expenses);
    }
}
