package com.example.fitnessclub.controller.command.impl.common;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class OpenPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = request.getParameter(AttributeName.PAGE);
        request.getSession().setAttribute(AttributeName.CURRENT_PAGE, page);
        return new Router(page);
    }
}
