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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class CheckServiceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static List<Service> services = new ArrayList<>();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        if (services.isEmpty()) {
            try {
                List<Service> services = ServiceServiceImpl.getInstance().findAll();
                request.getServletContext().setAttribute(AttributeName.SERVICES, services);
            } catch (ServiceException e) {
                logger.log(Level.ERROR,"Error service findAll()");
                throw new CommandException(e);
            }
            request.getSession().setAttribute(AttributeName.CURRENT_PAGE, PagePath.SERVICE);
        }
        return new Router(PagePath.SERVICE);
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
