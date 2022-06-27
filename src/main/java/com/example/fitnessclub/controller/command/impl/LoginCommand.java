package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(AttributeName.LOGIN);
        String pass = request.getParameter(AttributeName.PASSWORD);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            Optional<User> optionalUser = userService.authenticate(login, pass);
            if (optionalUser.isPresent()) {
                page = PagePath.INDEX;
                session.setAttribute(AttributeName.ROLE, optionalUser.get().getRole());
                switch (optionalUser.get().getRole()) {
                    case ADMIN -> session.setAttribute(AttributeName.ADMIN_SWITCH, false);
                    case TRAINER -> session.setAttribute(AttributeName.TRAINER_SWITCH, false);
                }
                session.setAttribute(AttributeName.LOGIN, optionalUser.get().getLogin());
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.INCORRECT_LOGIN_PASSWORD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
