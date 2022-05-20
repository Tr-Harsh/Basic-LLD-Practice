package com.Navi.Command;


import com.Navi.Constant.Constants;
import com.Navi.Model.Command;
import com.Navi.Model.Person;
import com.Navi.Service.BalanceProcessor;

import java.util.List;

public class BalanceCommandExecutor extends CommandExecutor {

    public static final String BALANCE = Constants.BALANCE;
    public final BalanceProcessor balanceProcessor = new BalanceProcessor();

    public BalanceCommandExecutor() {
    }

    public Boolean isApplicable(Command command) {
        return command.getName().equals(BALANCE);
    }

    protected List<Person> executeValidCommand(Command command, List<Person> personDirectory) throws Exception {
        return balanceProcessor.processBalance(command.getInput(), personDirectory);
    }
}
