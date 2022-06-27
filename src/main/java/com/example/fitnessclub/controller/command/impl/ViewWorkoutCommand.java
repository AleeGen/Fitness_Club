package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.entity.Workout;
import com.example.fitnessclub.model.service.impl.WorkoutServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ViewWorkoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        WorkoutServiceImpl workoutService = WorkoutServiceImpl.getInstance();
        try {
            Optional<User> optionalUser = userService.find(login);
            if (optionalUser.isPresent()) {
                List<Workout> workouts = workoutService.findAll(optionalUser.get().getId());
                ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.WORKOUTS, workouts);
                session.setAttribute(AttributeName.CURRENT_PAGE, PagePath.APPOINTMENTS);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(PagePath.APPOINTMENTS);
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
