package com.example.fitnessclub.command.impl;

import com.example.fitnessclub.command.Command;
import com.example.fitnessclub.constants.MassagePage;
import com.example.fitnessclub.constants.SessionAttributes;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String page = "index.jsp";
        HttpSession session = request.getSession();
        try {
            Optional<User> optionalUser = userService.authenticate(login, pass);
            if (optionalUser.isPresent()) {
                session.setAttribute(SessionAttributes.ALL_INFO_USER, optionalUser.get());
                page = "pages/main.jsp";
            } else {
                request.setAttribute("msg", MassagePage.INCORRECT_LOGIN_PASSWORD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
