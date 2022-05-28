package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;


public class RegistrationStepOneCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = PagePath.REGISTRATION_STEP_ONE;
        RequestParameters paramUser = new RequestParameters();
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_ONE);
        paramUser.put(AttributeName.LOGIN, request.getParameter(AttributeName.LOGIN));
        paramUser.put(AttributeName.PASSWORD, request.getParameter(AttributeName.PASSWORD));
        paramUser.put(AttributeName.NAME, request.getParameter(AttributeName.NAME));
        paramUser.put(AttributeName.LASTNAME, request.getParameter(AttributeName.LASTNAME));
        paramUser.put(AttributeName.MAIL, request.getParameter(AttributeName.MAIL));
        HttpSession session = request.getSession();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                //((HashMap) session.getAttribute(AttributeName.TEMP_SESSION)).put(AttributeName.USER, paramUser);
                //session.setAttribute(AttributeName.USER, paramUser);
                page = PagePath.REGISTRATION_STEP_TWO;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } /*else {
                request.setAttribute(AttributeName.USER, paramUser);
            }*/
            ((HashMap) session.getAttribute(AttributeName.TEMP_SESSION)).put(AttributeName.USER, paramUser);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
