package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Workout;
import com.example.fitnessclub.model.service.impl.WorkoutServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class AddAppointmentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String loginToChange = request.getParameter(AttributeName.WORKOUTS_BY_LOGIN);
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        Router.Type type = Router.Type.FORWARD;
        try {
            if (WorkoutServiceImpl.getInstance().canUserAdd(loginToChange, login)) {
                List<Workout> workouts = WorkoutServiceImpl.getInstance().add(loginToChange);
                if (!workouts.isEmpty()) {
                    ((HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.WORKOUTS, workouts);
                    type = Router.Type.REDIRECT;
                } else {
                    request.setAttribute(MessagePage.MESSAGE, MessagePage.ADD_INCORRECT);
                }
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.NO_ACCESS);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page, type);
    }
}
