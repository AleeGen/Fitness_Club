package com.example.fitnessclub.controller.command.impl.common;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.UserRole;
import com.example.fitnessclub.model.service.impl.ContractServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

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
                if (optionalUser.get().isBlocked()) {
                    request.setAttribute(MessagePage.MESSAGE, MessagePage.USER_BLOCKED);
                } else {
                    page = PagePath.INDEX;
                    session.setAttribute(AttributeName.ROLE, optionalUser.get().getRole());
                    if (optionalUser.get().getRole() == UserRole.ADMIN) {
                        session.setAttribute(AttributeName.ADMIN_SWITCH, false);
                    } else if (optionalUser.get().getRole() == UserRole.TRAINER) {
                        session.setAttribute(AttributeName.TRAINER_SWITCH, false);
                        List<User> clients = ContractServiceImpl.getInstance().findClients(login);
                        HashSet<String> clientLogins = new HashSet<>();
                        for (var client : clients) {
                            clientLogins.add(client.getLogin());
                        }
                        session.setAttribute(AttributeName.CLIENT_LOGINS, clientLogins);
                    }
                    session.setAttribute(AttributeName.LOGIN, optionalUser.get().getLogin());
                    session.setAttribute(AttributeName.CURRENT_PAGE, page);
                }
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.INCORRECT_LOGIN_PASSWORD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
