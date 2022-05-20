package com.Navi.Service;

import com.Navi.Constant.Constants;
import com.Navi.Model.Emi;
import com.Navi.Model.Loan;
import com.Navi.Model.Person;

import java.util.ArrayList;
import java.util.List;

public class LoanProcessor {

    public List<Person> processLoan(String input, List<Person> personDirectory) {
        String[] split = input.split(" ");
        String bankName = split[0];
        String personName = split[1];
        Double principleAmount = Double.parseDouble(split[2]);
        Integer years = Integer.parseInt(split[3]);
        Double rate = Double.parseDouble(split[4]);

        Loan loan = getLoan(bankName, principleAmount, rate, years);
        Person person = getPerson(personName, loan);
        personDirectory.add(person);
        return personDirectory;
    }

    private Person getPerson(String personName, Loan loan) {
        Person person = new Person();
        person.setName(personName);
        person.setLoans(new ArrayList<Loan>() {{
            add(loan);
        }});
        return person;
    }

    private Loan getLoan(String bankName, Double principleAmount, Double rate, Integer years) {
        Loan loan = new Loan(bankName, principleAmount, rate, years);
        Double amount = principleAmount + (principleAmount * years * rate) / Constants.PERCENT;
        loan.setAmount(amount);
        Double emiPerYear = amount / years;
        Integer emiAmount = (int) Math.ceil(emiPerYear / Constants.MONTHS_PER_YEAR);
        int lastEmiAmount = (int) (emiPerYear % emiAmount);
        int totalEmiNumber = Constants.MONTHS_PER_YEAR * years;
        loan.setLastEmi(totalEmiNumber);
        List<Emi> emis = new ArrayList<>();
        for (int i = 0; i < totalEmiNumber; i++) {
            Emi emi = new Emi(i+1, emiAmount, false);
            if (i == totalEmiNumber - 1){
                if(lastEmiAmount!=0) {
                    emi.setEmiAmount(lastEmiAmount);
                    emi.setLastEmi(true);
                }
            }
            emis.add(emi);
        }
        loan.setEmis(emis);

        return loan;
    }




}