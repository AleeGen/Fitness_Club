package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.ParameterName;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(ParameterName.LOGIN);
        String pass = request.getParameter(ParameterName.PASSWORD);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String page = PagePath.INDEX;
        HttpSession session = request.getSession();
        try {
            Optional<User> optionalUser = userService.authenticate(login, pass);
            if (optionalUser.isPresent()) {
                page = PagePath.MAIN;
                //request.setAttribute(AttributeName.USER, optionalUser.get());
                //session.setAttribute(AttributeName.USER, optionalUser.get());
                ((HashMap) session.getAttribute("temp")).put(AttributeName.USER, optionalUser.get());
                session.setAttribute(AttributeName.ROLE, optionalUser.get().getRole());
                session.setAttribute(AttributeName.USER_ID, optionalUser.get().getId());
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
