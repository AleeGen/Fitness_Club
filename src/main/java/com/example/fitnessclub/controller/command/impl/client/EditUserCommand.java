package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Optional;

public class EditUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RequestParameters paramUser = new RequestParameters();
        paramUser.put(AttributeName.LOGIN, (String) session.getAttribute(AttributeName.LOGIN));
        paramUser.put(AttributeName.NAME, request.getParameter(AttributeName.NAME));
        paramUser.put(AttributeName.LASTNAME, request.getParameter(AttributeName.LASTNAME));
        paramUser.put(AttributeName.MAIL, request.getParameter(AttributeName.MAIL));
        paramUser.put(AttributeName.PHONE, request.getParameter(AttributeName.PHONE));
        paramUser.put(AttributeName.DATE_BIRTH, request.getParameter(AttributeName.DATE_BIRTH));
        paramUser.put(AttributeName.SEX, request.getParameter(AttributeName.SEX));
        paramUser.put(AttributeName.NUMBER_CARD, request.getParameter(AttributeName.NUMBER_CARD));
        paramUser.put(AttributeName.ABOUT_ME, request.getParameter(AttributeName.ABOUT_ME));
        try {
            Optional<User> optionalUser = UserServiceImpl.getInstance().update(paramUser);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(AttributeName.USER_PARAM, paramUser);
                ((HashMap<String, Object>) request.getSession().getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.USER, user);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error when updating the user");
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
