package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.RequestParameters;
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

public class EditAppointmentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RequestParameters paramWorkout = new RequestParameters();
        String appointmentId = request.getParameter(AttributeName.APPOINTMENT_ID);
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        try {
            if (WorkoutServiceImpl.getInstance().canUserChange(appointmentId, login)) {
                paramWorkout.put(AttributeName.APPOINTMENT_ID, appointmentId);
                paramWorkout.put(AttributeName.DATE, request.getParameter(AttributeName.DATE));
                paramWorkout.put(AttributeName.APPOINTMENT_TYPE, request.getParameter(AttributeName.APPOINTMENT_TYPE));
                paramWorkout.put(AttributeName.NUTRITION, request.getParameter(AttributeName.NUTRITION));
                paramWorkout.put(AttributeName.EXERCISE_ID, request.getParameterValues(AttributeName.EXERCISE_ID));
                paramWorkout.put(AttributeName.EXERCISE_NAME, request.getParameterValues(AttributeName.EXERCISE_NAME));
                paramWorkout.put(AttributeName.NUMBER_SETS, request.getParameterValues(AttributeName.NUMBER_SETS));
                paramWorkout.put(AttributeName.NUMBER_REPETITIONS, request.getParameterValues(AttributeName.NUMBER_REPETITIONS));
                paramWorkout.put(AttributeName.EQUIPMENT, request.getParameterValues(AttributeName.EQUIPMENT));
                paramWorkout.put(AttributeName.DESCRIPTION, request.getParameterValues(AttributeName.DESCRIPTION));
                Optional<Workout> workoutOptional = WorkoutServiceImpl.getInstance().update(paramWorkout);
                if (workoutOptional.isPresent()) {
                    ((HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.WORKOUT, workoutOptional.get());
                } else {
                    request.setAttribute(MessagePage.MESSAGE, MessagePage.FAILED_UPDATE_APPOINTMENT);
                }
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.NO_ACCESS);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
