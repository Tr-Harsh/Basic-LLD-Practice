package com.Navi.Service;

import com.Navi.Model.Emi;
import com.Navi.Model.Loan;
import com.Navi.Model.Person;

import java.util.List;

public class PaymentProcessor {
        public List<Person> processPayment(String input, List<Person> personDirectory) throws Exception {
        String[] split = input.split(" ");
        String bankName = split[0];
        String personName = split[1];
        Double lumpSumAmount = Double.parseDouble(split[2]);
        Integer emiNumber = Integer.parseInt(split[3]);
        return calculateAndSetBalanceForEmi(bankName, personName, emiNumber, lumpSumAmount, personDirectory);
    }

    private List<Person> calculateAndSetBalanceForEmi(String bankName, String personName, Integer emiNumber, Double lumpSumAmount, List<Person> personDirectory) throws Exception {
        Integer personIndex = findPersonByNameAndBankName(bankName, personName, personDirectory);
        if (personIndex != -1) {
            double totalAmount = 0.0;
            int emiPerMonth = 0;
            int amountPaid = 0;
            Person person = personDirectory.get(personIndex);
            for (Loan loan : person.getLoans()) {
                if (loan.getBankName().equals(bankName)) {
                    for (Emi emi : loan.getEmis()) {
                        if (emi.getNumber()-1 < emiNumber) {
                            amountPaid += emi.getEmiAmount() + emi.getLumpSumAmount();
                        }
                        if (emiNumber==emi.getNumber()-1) {
                            emi.setLumpSumAmount(lumpSumAmount);
                            amountPaid += emi.getEmiAmount() + emi.getLumpSumAmount();
                            amountPaid += loan.getEmis().get(emi.getNumber()).getLumpSumAmount();
                            emiPerMonth = emi.getEmiAmount();
                            break;
                        }
                    }
                    totalAmount = loan.getAmount();
                }
            }
            personDirectory = setBalanceForEmi(bankName, personName, totalAmount, amountPaid, emiNumber, emiPerMonth, personDirectory, personIndex);
        }

        return personDirectory;
    }

    private List<Person> setBalanceForEmi(String bankName, String personName, double totalAmount, double amountPaid, int emiNumber, int emiPerMonth, List<Person> personDirectory, Integer personIndex) throws Exception {

        Double remainingAmount = totalAmount - amountPaid;
        int remainingEmis = (int) Math.ceil(remainingAmount / emiPerMonth);
        Person person = personDirectory.get(personIndex);
        for (Loan loan : person.getLoans()) {
            if (loan.getBankName().equals(bankName)) {
                List<Emi> emis = loan.getEmis();
                int last = emis.size() - 1;
                int emiAmount = emis.get(last).getEmiAmount();
                int lastEmiAmount = emiAmount;
                if(remainingAmount < 0) {
                    for(int furtherEmi=emiNumber;furtherEmi<emis.size();furtherEmi++){
                        emis.get(furtherEmi).setLastEmi(false);
                        emis.get(furtherEmi).setEmiAmount(0);
                        emis.get(furtherEmi).setLumpSumAmount(0);
                    }
                    emis.get(emiNumber).setEmiAmount(lastEmiAmount);
                    emis.get(emiNumber).setLastEmi(true);
                    throw new Exception("Principal amount exceeded. User eligible for cash: "+-remainingAmount);
                }
                if (remainingAmount <= emiAmount) {
                    last = emiNumber;
                    lastEmiAmount = remainingAmount.intValue();
                }
                else {
                    last = emiNumber + remainingEmis - 1;
                    lastEmiAmount = (int) (remainingAmount % emiAmount);
                }
                emis.get(last).setEmiAmount(lastEmiAmount);
                emis.get(last).setLastEmi(true);

                for (int furtherEmi = last + 1; furtherEmi < emis.size(); furtherEmi++) {
                    emis.get(furtherEmi).setLastEmi(false);
                    emis.get(furtherEmi).setEmiAmount(0);
                    emis.get(furtherEmi).setLumpSumAmount(0);
                }
                loan.setLastEmi(last+1);
                loan.setEmis(emis);
            }
        }
        return personDirectory;
    }

    private Integer findPersonByNameAndBankName(String bankName, String personName, List<Person> personDirectory) {
        for (int i = 0; i < personDirectory.size(); i++) {
            Person person = personDirectory.get(i);
            if (person.getName().equals(personName)) {
                for (Loan loan : person.getLoans()) {
                    if (loan.getBankName().equals(bankName)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
