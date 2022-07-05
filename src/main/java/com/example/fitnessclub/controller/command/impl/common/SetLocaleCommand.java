package com.example.fitnessclub.controller.command.impl.common;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SetLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(AttributeName.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.LOCALE, locale);
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
