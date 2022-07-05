package com.example.fitnessclub.controller.command.impl.common;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class RegistrationStepTwoCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
        RequestParameters paramUser = (RequestParameters) tempAttr.get(AttributeName.USER);
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_TWO);
        paramUser.put(AttributeName.PHONE, request.getParameter(AttributeName.PHONE));
        paramUser.put(AttributeName.DATE_BIRTH, request.getParameter(AttributeName.DATE_BIRTH));
        paramUser.put(AttributeName.SEX, request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.NUMBER_CARD, request.getParameter(AttributeName.NUMBER_CARD));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router.Type type = Router.Type.REDIRECT;
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        try {
            if (userService.registration(paramUser)) {
                page = PagePath.INDEX;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                tempAttr.put(AttributeName.USER, paramUser);
                type = Router.Type.FORWARD;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page, type);
    }
}
