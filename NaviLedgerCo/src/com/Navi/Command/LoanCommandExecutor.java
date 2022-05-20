package com.Navi.Command;

import com.Navi.Constant.Constants;
import com.Navi.Model.Command;
import com.Navi.Model.Person;
import com.Navi.Service.LoanProcessor;

import java.util.List;

public class LoanCommandExecutor extends CommandExecutor{
    public static final String LOAN = Constants.LOAN;
    public final LoanProcessor LoanProcessor = new LoanProcessor();

    public LoanCommandExecutor() {
    }

    public Boolean isApplicable(Command command) {
        return command.getName().equals(LOAN);
    }


    protected List<Person> executeValidCommand(Command command, List<Person> personDirectory) {
        return LoanProcessor.processLoan(command.getInput(), personDirectory);
    }
}
