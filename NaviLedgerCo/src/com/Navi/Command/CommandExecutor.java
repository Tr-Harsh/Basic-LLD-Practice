package com.Navi.Command;

import com.Navi.Model.Command;
import com.Navi.Model.Person;

import java.util.List;

abstract public class CommandExecutor {

    public CommandExecutor() {
    }

    public List<Person> execute(final Command command, List<Person> personDirectory) throws Exception {
        return executeValidCommand(command, personDirectory);
    }

    public abstract Boolean isApplicable(final Command command);

    protected abstract List<Person> executeValidCommand(final Command command, List<Person> personDirectory) throws Exception;
}