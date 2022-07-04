package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
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

public class ViewPersonalTrainerCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        Optional<User> trainer;
        try {
            trainer = UserServiceImpl.getInstance().findTrainerClient(login);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        if (trainer.isPresent()) {
            HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
            tempAttr.put(AttributeName.USER, trainer.get());
            page = PagePath.PROFILE;
            session.setAttribute(AttributeName.CURRENT_PAGE, page);
        } else {
            request.setAttribute(MessagePage.MESSAGE, MessagePage.NO_TRAINER);
        }
        return new Router(page);
    }
}