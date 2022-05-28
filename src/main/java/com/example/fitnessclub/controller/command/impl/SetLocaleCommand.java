package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class SetLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(AttributeName.LOCALE);
        request.getSession().setAttribute(AttributeName.LOCALE, locale);
        return new Router((String)request.getSession().getAttribute(AttributeName.CURRENT_PAGE));
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
