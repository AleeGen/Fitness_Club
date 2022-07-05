package com.example.fitnessclub.controller.command.impl.common;

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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class RegistrationStepOneCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        RequestParameters paramUser = new RequestParameters();
        paramUser.put(AttributeName.STEP_NUMBER, AttributeName.STEP_ONE);
        paramUser.put(AttributeName.LOGIN, request.getParameter(AttributeName.LOGIN));
        paramUser.put(AttributeName.PASSWORD, request.getParameter(AttributeName.PASSWORD));
        paramUser.put(AttributeName.REPEAT_PASSWORD, request.getParameter(AttributeName.REPEAT_PASSWORD));
        paramUser.put(AttributeName.NAME, request.getParameter(AttributeName.NAME));
        paramUser.put(AttributeName.LASTNAME, request.getParameter(AttributeName.LASTNAME));
        paramUser.put(AttributeName.MAIL, request.getParameter(AttributeName.MAIL));
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(paramUser)) {
                page = PagePath.REGISTRATION_STEP_TWO;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            }
            ((HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.USER, paramUser);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
