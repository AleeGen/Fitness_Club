package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.model.mapper.ColumnName;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationStepTwoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        System.out.println("+stepTwo+");
        String page = PagePath.REGISTRATION_STEP_TWO;
        System.out.println("==>");
        System.out.println(request.getSession().getAttribute(AttributeName.ATTRIBUTE_USER));
        RequestParameters paramUser = (RequestParameters) request.getSession().getAttribute(AttributeName.ATTRIBUTE_USER);
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_TWO);
        paramUser.put(ColumnName.PHONE, request.getParameter(ColumnName.PHONE));
        paramUser.put(ColumnName.DATE_BIRTH, request.getParameter(ColumnName.DATE_BIRTH));
        System.out.println("------>");
        System.out.println(request.getParameter(ColumnName.SEX));
        paramUser.put(ColumnName.SEX, request.getParameter(ColumnName.SEX));
        paramUser.put(ColumnName.NUMBER_CARD, request.getParameter(ColumnName.NUMBER_CARD));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                System.out.println("registration2");
                page = PagePath.INDEX;
                request.getSession().removeAttribute(AttributeName.ATTRIBUTE_USER);
                request.setAttribute(MessagePage.MESSAGE, MessagePage.REGISTRATION_SUCCESSFUL);
                request.getSession().setAttribute(AttributeName.CURRENT_PAGE,page);
            } else {
                System.out.println("not registration");
                System.out.println(paramUser);
                request.getSession().setAttribute(AttributeName.ATTRIBUTE_USER, paramUser);
                System.out.println(request.getSession().getAttribute(AttributeName.ATTRIBUTE_USER));
            }
        } catch (ServiceException e) {
            //request.getSession().removeAttribute(RequestAttributeName.ATTRIBUTE_USER);  //// TODO: 12.05.2022 Если я удаляю сессию, то второй этап регистрации рушится из-за paramUser=null. В какой момент лучше удалять сессию? 
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
