package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

public class RegistrationStepTwoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = PagePath.REGISTRATION_STEP_TWO;
        RequestParameters paramUser = (RequestParameters) ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION)).get(AttributeName.USER);
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_TWO);
        paramUser.put(AttributeName.PHONE, request.getParameter(AttributeName.PHONE));
        paramUser.put(AttributeName.DATE_BIRTH, request.getParameter(AttributeName.DATE_BIRTH));
        paramUser.put(AttributeName.SEX, request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.NUMBER_CARD, request.getParameter(AttributeName.NUMBER_CARD));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                page = PagePath.LOGIN;
                request.setAttribute(MessagePage.MESSAGE, MessagePage.REGISTRATION_SUCCESSFUL);
                request.getSession().setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION)).put(AttributeName.USER, paramUser);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
