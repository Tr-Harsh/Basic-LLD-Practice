package com.Navi.Command;

import com.Navi.Model.Command;
import com.Navi.Model.Person;

import java.util.List;

public class CommandRunner {
    List<CommandExecutor> commandExecutors;

    public CommandRunner(List<CommandExecutor> commandExecutors) {
        this.commandExecutors = commandExecutors;
    }

    public List<Person> runCommand(Command command, List<Person> personDirectory) throws Exception {
        for (CommandExecutor commandExecutor: commandExecutors) {
            if (commandExecutor.isApplicable(command)) {
                return commandExecutor.execute(command, personDirectory);
            }
        }
        return null;
    }
}

