package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditPasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        RequestParameters parameters = new RequestParameters();
        HttpSession session = request.getSession();
        parameters.put(AttributeName.PASSWORD, request.getParameter(AttributeName.PASSWORD));
        parameters.put(AttributeName.REPLACE_PASSWORD, request.getParameter(AttributeName.REPLACE_PASSWORD));
        parameters.put(AttributeName.REPEAT_PASSWORD, request.getParameter(AttributeName.REPEAT_PASSWORD));
        parameters.put(AttributeName.LOGIN, (String) session.getAttribute(AttributeName.LOGIN));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.editPassword(parameters)) {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.PASSWORD_CHANGED);
            } else {
                request.setAttribute(AttributeName.USER_PARAM, parameters);
                request.setAttribute(MessagePage.MESSAGE, MessagePage.PASSWORD_NOT_CHANGED);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error when changing the password");
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
