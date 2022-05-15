package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.model.mapper.ColumnName;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class RegistrationStepOneCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = PagePath.REGISTRATION_STEP_ONE;
        RequestParameters paramUser = new RequestParameters();
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_ONE);
        paramUser.put(ColumnName.LOGIN, request.getParameter(ColumnName.LOGIN));
        paramUser.put(ColumnName.PASSWORD, request.getParameter(ColumnName.PASSWORD));
        paramUser.put(ColumnName.NAME, request.getParameter(ColumnName.NAME));
        paramUser.put(ColumnName.LASTNAME, request.getParameter(ColumnName.LASTNAME));
        paramUser.put(ColumnName.MAIL, request.getParameter(ColumnName.MAIL));
        HttpSession session = request.getSession();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                page = PagePath.REGISTRATION_STEP_TWO;
                session.setAttribute(AttributeName.ATTRIBUTE_USER, paramUser);
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                request.setAttribute(AttributeName.ATTRIBUTE_USER, paramUser);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
