package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.*;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;

public class EditAvatarCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String AVATAR = "avatar";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Part part;
        try {
            part = request.getPart(AVATAR);
        } catch (IOException | ServletException e) {
            logger.log(Level.ERROR, "Error when executing request.getPart(AVATAR)");
            throw new CommandException(e);
        }
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        try {
            if (UserServiceImpl.getInstance().editAvatar(part, login)) {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.UPLOAD_SUCCESSFULLY);
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.UPLOAD_FAILED);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error when executing userService.editAvatar");
            throw new CommandException(e);
        }
        return new Router((String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE));
    }
}
