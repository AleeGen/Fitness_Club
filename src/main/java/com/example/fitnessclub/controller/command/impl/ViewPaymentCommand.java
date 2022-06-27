package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewPaymentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        boolean status = Boolean.valueOf(request.getParameter(AttributeName.PAYMENT_STATUS));
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        try {
            List<Payment> payments = PaymentServiceImpl.getInstance().findAll(login, status);
            ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.PAYMENTS, payments);
            ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE)).put(AttributeName.PAID, status);
            page = PagePath.PAYMENTS;
            session.setAttribute(AttributeName.CURRENT_PAGE, page);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(page);
    }


    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
