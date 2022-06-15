package com.example.fitnessclub.controller.command;


import com.example.fitnessclub.controller.command.impl.*;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    REGISTRATION_STEP_ONE(new RegistrationStepOneCommand()),
    REGISTRATION_STEP_TWO(new RegistrationStepTwoCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    OPEN_PAGE(new OpenPageCommand()),
    ADD_TO_CART(new AddToCartCommand()),
    SET_LOCALE(new SetLocaleCommand()),
    VIEW_PROFILE(new ViewProfileCommand()),
    VIEW_INDEX(new ViewIndexCommand()),
    EDIT_AVATAR(new EditAvatarCommand()),
    EDIT_USER(new EditUserCommand()),
    EDIT_PASSWORD(new EditPasswordCommand()),
    VIEW_APPOINTMENTS(new ViewAppointmentsCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        if (commandStr == null) {
            return DEFAULT.command;
        }
        Optional<CommandType> command = Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.name().equals(commandStr.toUpperCase()))
                .findFirst();
        return command.isPresent() ? command.get().command : DEFAULT.command;
    }
}
