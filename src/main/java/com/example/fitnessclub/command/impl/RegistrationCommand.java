package com.example.fitnessclub.command.impl;

import com.example.fitnessclub.command.Command;
import com.example.fitnessclub.constants.ColumnName;
import com.example.fitnessclub.constants.MassagePage;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.service.impl.UserServiceImpl;
import com.example.fitnessclub.validation.ValidationUser;
import jakarta.servlet.http.HttpServletRequest;


public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        System.out.println("->21");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String page = "pages/registrationStepOne.jsp";
        RequestParameters paramUser = new RequestParameters();
        System.out.println("->22");
        paramUser.put(ColumnName.LOGIN, request.getParameter(ColumnName.LOGIN));
        paramUser.put(ColumnName.PASSWORD, request.getParameter(ColumnName.PASSWORD));
        paramUser.put(ColumnName.NAME, request.getParameter(ColumnName.NAME));
        paramUser.put(ColumnName.LASTNAME, request.getParameter(ColumnName.LASTNAME));
        paramUser.put(ColumnName.MAIL, request.getParameter(ColumnName.MAIL));
        paramUser.put(ColumnName.PHONE, request.getParameter(ColumnName.PHONE));
        paramUser.put(ColumnName.DATE_BIRTH, request.getParameter(ColumnName.DATE_BIRTH));
        paramUser.put(ColumnName.SEX, request.getParameter(ColumnName.SEX));
        paramUser.put(ColumnName.NUMBER_CARD, request.getParameter(ColumnName.NUMBER_CARD));
        System.out.println("->23");
        ValidationUser validation = new ValidationUser();
        if (validation.isValidLogin(login) && validation.isValidPassword(password)) {  //// TODO: 04.05.2022 валидацию нужно перенести в сервис, подумать что должен возвращать сервис, чтобы мы могли проверить валидность и получить текст об ошибке 
            System.out.println("->24");
            UserServiceImpl userService = UserServiceImpl.getInstance();
            try {
                if (userService.registration(paramUser)) {
                    System.out.println("->25");
                    request.setAttribute("msg", MassagePage.REGISTRATION_SUCCESSFUL);
                    page = "index.jsp";
                } else {
                    System.out.println("->26");
                    request.setAttribute("msg_failed", MassagePage.USER_EXISTS);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            System.out.println("->27");
            request.setAttribute("msg_failed", MassagePage.VALIDATION_LOGIN_PASSWORD);
        }
        System.out.println("->28");
        return page;
    }
}
