package com.example.fitnessclub.controller.command.impl.admin;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
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
            HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
            String login = ((User) tempAttr.get(AttributeName.USER)).getLogin();
            String roleUser = request.getParameter(AttributeName.ROLE);
            String corporate = request.getParameter(AttributeName.CORPORATE);
            try {
                if (UserServiceImpl.getInstance().editFeatures(login, roleUser, corporate)) {
                    Optional<User> user = UserServiceImpl.getInstance().findByLogin(login);
                    user.ifPresent(value -> tempAttr.put(AttributeName.USER, value));
                } else {
                    request.setAttribute(MessagePage.MESSAGE, MessagePage.EDIT_USER_FAILED);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
