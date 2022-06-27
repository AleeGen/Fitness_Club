package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;

public class RegistrationStepTwoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        RequestParameters paramUser = (RequestParameters) ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_ATTRIBUTE)).get(AttributeName.USER);
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_TWO);
        paramUser.put(AttributeName.PHONE, request.getParameter(AttributeName.PHONE));
        paramUser.put(AttributeName.DATE_BIRTH, request.getParameter(AttributeName.DATE_BIRTH));
        paramUser.put(AttributeName.SEX, request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.NUMBER_CARD, request.getParameter(AttributeName.NUMBER_CARD));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router.Type type = Router.Type.REDIRECT;
        try {
            if (userService.registration(paramUser)) { //// TODO: 11.06.2022 сообщение как передать об успешности, если redirect стирает 
                page = PagePath.INDEX;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.USER, paramUser);
                type = Router.Type.FORWARD;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page, type);
    }


}
