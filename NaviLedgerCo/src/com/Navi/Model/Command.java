package com.Navi.Model;

public class Command {
    String name;
    String input;

    public Command() {
    }

    public Command(String name, String input) {
        this.name = name;
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", input='" + input + '\'' +
                '}';
    }
}
