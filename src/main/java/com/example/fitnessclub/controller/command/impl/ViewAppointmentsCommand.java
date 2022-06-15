package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.AppointmentService;
import com.example.fitnessclub.model.service.impl.AppointmentServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ViewAppointmentsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        AppointmentServiceImpl appointmentService = AppointmentServiceImpl.getInstance();
        try {
            Optional<User> optionalUser = userService.find(login);
            if (optionalUser.isPresent()) {
                List<Appointment> appointments = appointmentService.findAll(optionalUser.get().getId());
                System.out.println("size = "+appointments.size());
                ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.APPOINTMENTS, appointments);
                session.setAttribute(AttributeName.CURRENT_PAGE,"pages/test.jsp");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router("pages/test.jsp");
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
