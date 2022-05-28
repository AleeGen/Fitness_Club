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
        System.out.println("+stepTwo+");
        String page = PagePath.REGISTRATION_STEP_TWO;
        System.out.println("info registration step 2==>");
        System.out.println(((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION)).get(AttributeName.USER));
        RequestParameters paramUser = (RequestParameters) ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION)).get(AttributeName.USER);
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_TWO);
        paramUser.put(AttributeName.PHONE, request.getParameter(AttributeName.PHONE));
        paramUser.put(AttributeName.DATE_BIRTH, request.getParameter(AttributeName.DATE_BIRTH));
        System.out.println("------>");
        System.out.println(request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.SEX, request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.NUMBER_CARD, request.getParameter(AttributeName.NUMBER_CARD));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                System.out.println("registration2");
                page = PagePath.INDEX;
                //request.getSession().removeAttribute(AttributeName.USER);
                request.setAttribute(MessagePage.MESSAGE, MessagePage.REGISTRATION_SUCCESSFUL);
                request.getSession().setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                System.out.println("not registration");
                System.out.println(paramUser);
                ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION)).put(AttributeName.USER, paramUser);
                //request.getSession().setAttribute(AttributeName.USER, paramUser);
                System.out.println(request.getSession().getAttribute(AttributeName.USER));
            }
        } catch (ServiceException e) {
            //request.getSession().removeAttribute(RequestAttributeName.ATTRIBUTE_USER);  //// TODO: 12.05.2022 Если я удаляю сессию, то второй этап регистрации рушится из-за paramUser=null. В какой момент лучше удалять сессию?
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
