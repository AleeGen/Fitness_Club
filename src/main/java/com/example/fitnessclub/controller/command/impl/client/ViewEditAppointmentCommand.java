package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.PagePath;
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
import java.util.Optional;

public class ViewEditAppointmentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String appointmentId = request.getParameter(AttributeName.APPOINTMENT_ID);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        try {
            Optional<Workout> optionalWorkout = WorkoutServiceImpl.getInstance().find(appointmentId);
            if (optionalWorkout.isPresent()) {
                HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                tempAttr.put(AttributeName.WORKOUT, optionalWorkout.get());
                page = PagePath.EDIT_APPOINTMENT;
                session.setAttribute(AttributeName.CURRENT_PAGE, page);
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.WORKOUT_NOT_FOUND);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
