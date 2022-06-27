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

import java.util.HashMap;
import java.util.Optional;

public class ViewEditProfileCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        try {
            Optional<User> optionalUser = UserServiceImpl.getInstance().find(login);
            if (optionalUser.isPresent()) {
                page = PagePath.PROFILE_EDIT;
                ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.USER, optionalUser.get());
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
