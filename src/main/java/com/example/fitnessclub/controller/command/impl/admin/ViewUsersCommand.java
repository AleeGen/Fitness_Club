package com.example.fitnessclub.controller.command.impl.admin;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
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
import java.util.List;

public class ViewUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        UserRole role = (UserRole) session.getAttribute(AttributeName.ROLE);
        if (role == UserRole.ADMIN) {
            try {
                List<User> users = UserServiceImpl.getInstance().findAll();
                ((HashMap<String,Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.USERS, users);
                page = PagePath.LIST_USERS;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
        }
        return new Router(page);
    }
}
