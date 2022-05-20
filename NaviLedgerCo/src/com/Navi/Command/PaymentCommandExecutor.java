package com.Navi.Command;

import com.Navi.Constant.Constants;
import com.Navi.Model.Command;
import com.Navi.Model.Person;
import com.Navi.Service.PaymentProcessor;

import java.util.List;

public class PaymentCommandExecutor extends CommandExecutor{
    public static final String PAYMENT = Constants.PAYMENT;
    public final PaymentProcessor paymentProcessor = new PaymentProcessor();

    public PaymentCommandExecutor() {
    }

    public Boolean isApplicable(Command command) {
        return command.getName().equals(PAYMENT);
    }


    protected List<Person> executeValidCommand(Command command, List<Person> personDirectory) throws Exception {
        return paymentProcessor.processPayment(command.getInput(), personDirectory);
    }
}
