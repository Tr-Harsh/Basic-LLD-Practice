package com.Navi.Service;

import com.Navi.Model.Emi;
import com.Navi.Model.Loan;
import com.Navi.Model.Person;

import java.util.List;

public class BalanceProcessor {
        public List<Person> processBalance(String input, List<Person> personDirectory) throws Exception {
        String[] split = input.split(" ");
        String bankName = split[0];
        String personName = split[1];
        Integer emiNumber = Integer.parseInt(split[2]);
        return getBalanceforPersonAndBankUptoEmi(bankName, personName, emiNumber, personDirectory);
    }

    private List<Person> getBalanceforPersonAndBankUptoEmi(String bankName, String personName, Integer emiNumber, List<Person> personDirectory) throws Exception {
        Integer personIndex = findPersonByNameAndBankName(bankName, personName, personDirectory);
        if (personIndex != -1) {
            Person person = personDirectory.get(personIndex);
            double amountPaid = 0.0;
            int emiPerMonth = 0;
            double totalAmount = 0.0;
            for (Loan loan : person.getLoans()) {
                if (loan.getBankName().equals(bankName)) {
                    if(emiNumber==0){
                        amountPaid = loan.getEmis().get(emiNumber).getLumpSumAmount();
                        emiPerMonth = loan.getEmis().get(emiNumber).getEmiAmount();
                        totalAmount = loan.getAmount();
                        break;
                    }
                    else {
                        if (emiNumber >= loan.getLastEmi()) throw new Exception("EMI number exceeds last payable EMI");
                        for (Emi emi : loan.getEmis()) {
                            if (emi.getNumber() <= emiNumber) {
                                amountPaid += emi.getEmiAmount() + emi.getLumpSumAmount();
                            } else {
                                emiPerMonth = emi.getEmiAmount();
                                totalAmount = loan.getAmount();
                                break;
                            }
                        }
                    }
                }
            }
            double remainingAmount = totalAmount - amountPaid;
            int remainingEmis = (int) Math.ceil(remainingAmount / emiPerMonth);
            printBalance(bankName, personName, amountPaid, remainingEmis);
        }
        return personDirectory;
    }



    private void printBalance(String bankName, String personName, Double amountPaid, Integer remainingEmis) {
        System.out.println(bankName + " " + personName + " " + amountPaid.intValue() + " " + remainingEmis);
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
