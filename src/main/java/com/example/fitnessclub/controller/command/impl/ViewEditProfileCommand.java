package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
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

public class ViewEditProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        try {
            Optional<User> optionalUser = UserServiceImpl.getInstance().findByLogin(login);
            if (optionalUser.isPresent()) {
                page = PagePath.PROFILE_EDIT;
                HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                tempAttr.put(AttributeName.USER, optionalUser.get());
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
