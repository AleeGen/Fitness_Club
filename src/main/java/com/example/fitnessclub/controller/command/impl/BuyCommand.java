package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BuyCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String SUCCESSFULLY = "Purchase was successful";
    private static final String NOT_SUCCESSFULLY = "Purchase was not successful";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long paymentId = Long.parseLong(request.getParameter(AttributeName.PAYMENT_ID));
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        try {
            if (UserServiceImpl.getInstance().buy(login, paymentId)) {
                request.setAttribute(MessagePage.MESSAGE, SUCCESSFULLY);
                HashMap tempAttribute = ((HashMap) session.getAttribute(AttributeName.TEMP_ATTRIBUTE));
                boolean status = (boolean) tempAttribute.get(AttributeName.PAID);
                List<Payment> payments = PaymentServiceImpl.getInstance().findAll(login, status);
                tempAttribute.put(AttributeName.PAYMENTS, payments);
            } else {
                request.setAttribute(MessagePage.MESSAGE, NOT_SUCCESSFULLY);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
