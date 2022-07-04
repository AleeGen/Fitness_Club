package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
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

public class UpAccountBalanceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String cash = request.getParameter(AttributeName.CASH);
        HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
        try {
            if (UserServiceImpl.getInstance().plusCash(login, cash)) {
                tempAttr.put(MessagePage.MESSAGE, MessagePage.PLUS_CASH_SUCCESSFULLY);
                Optional<User> optionalUser = UserServiceImpl.getInstance().findByLogin(login);
                optionalUser.ifPresent(user -> tempAttr.put(AttributeName.USER, user));
            } else {
                tempAttr.put(MessagePage.MESSAGE, MessagePage.PLUS_CASH_FAILED);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE), Router.Type.REDIRECT);
    }
}
