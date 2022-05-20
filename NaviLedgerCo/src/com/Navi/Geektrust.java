package com.Navi;

import com.Navi.Command.*;
import com.Navi.Model.Command;
import com.Navi.Model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Geektrust {

    public static void main(String[] args) {
        String filePath = args[0];
        File inputFile = new File(filePath);
        CommandRunner commandRunner = initCommandRunner();
        List<Person> personDirectory = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                personDirectory = commandRunner.runCommand(formCommand(line),personDirectory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Command formCommand(String line) {
        String name = line.substring(0,line.indexOf(" "));
        String input = line.substring(line.indexOf(" ")+1);
        return new Command(name,input);
    }

    private static CommandRunner initCommandRunner() {
        List<CommandExecutor> commandExecutorList = new ArrayList<CommandExecutor>() {{
            add(new BalanceCommandExecutor());
            add(new PaymentCommandExecutor());
            add(new LoanCommandExecutor());
        }};
        return new CommandRunner(commandExecutorList);
    }
}
