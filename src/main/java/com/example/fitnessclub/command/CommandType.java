package com.example.fitnessclub.command;

import com.example.fitnessclub.command.impl.AddUserCommand;
import com.example.fitnessclub.command.impl.DefaultCommand;
import com.example.fitnessclub.command.impl.LoginCommand;
import com.example.fitnessclub.command.impl.LogoutCommand;

import java.util.Locale;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) { //// TODO: 17.04.2022 валидность добавить
        CommandType current = CommandType.valueOf(commandStr.toUpperCase(Locale.ROOT));
        return current.command;  //// TODO: 17.04.2022 не понял, command ведь пустой... разобраться
    }
}
