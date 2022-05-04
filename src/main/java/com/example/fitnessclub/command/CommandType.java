package com.example.fitnessclub.command;

import com.example.fitnessclub.command.impl.*;

import java.util.Locale;

public enum CommandType {
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    OPEN_PAGE(new OpenPageCommand()),
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
