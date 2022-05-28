package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.service.impl.ServiceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

public class CheckServiceCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            List<Service> services = ServiceServiceImpl.getInstance().findAll();
            HashMap temp = ((HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION));
            temp.put(AttributeName.SERVICES, services);
        } catch (ServiceException e) {
            //log
            throw new CommandException(e);
        }
        request.getSession().setAttribute(AttributeName.CURRENT_PAGE, PagePath.SERVICE);
        return new Router(PagePath.SERVICE);
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
