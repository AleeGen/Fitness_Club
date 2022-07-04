package com.example.fitnessclub.controller.command.impl.admin;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.entity.UserRole;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Optional;

public class EditUserFeaturesCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(AttributeName.ROLE);
        if (role == UserRole.ADMIN) {
            RequestParameters paramUser = new RequestParameters();
            HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
            String login = ((User) tempAttr.get(AttributeName.USER)).getLogin();
            paramUser.put(AttributeName.LOGIN, login);
            paramUser.put(AttributeName.ROLE, request.getParameter(AttributeName.ROLE));
            paramUser.put(AttributeName.CORPORATE, request.getParameter(AttributeName.CORPORATE));
            paramUser.put(AttributeName.DISCOUNT, request.getParameter(AttributeName.DISCOUNT));
            try {
                Optional<User> optionalUser = UserServiceImpl.getInstance().editFeatures(paramUser);
                optionalUser.ifPresent(user -> tempAttr.put(AttributeName.USER, user));
                request.setAttribute(AttributeName.USER_PARAM, paramUser);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
