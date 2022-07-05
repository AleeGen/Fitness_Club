package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class ArrangeBuyCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String paymentId = request.getParameter(AttributeName.PAYMENT_ID);
        boolean correct = false;
        try {
            Optional<Payment> payment = PaymentServiceImpl.getInstance().find(paymentId);
            if (payment.isPresent()) {
                long serviceId = payment.get().getServiceId();
                Optional<Short> cost = PaymentServiceImpl.getInstance().calculateCost(login, serviceId);
                Optional<LocalDate> expiry = PaymentServiceImpl.getInstance().calculateExpiry(serviceId);
                if (cost.isPresent() && expiry.isPresent()) {
                    HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                    Optional<User> client = UserServiceImpl.getInstance().findByLogin(login);
                    if (client.isPresent()) {
                        tempAttr.put(AttributeName.COST, cost.get());
                        tempAttr.put(AttributeName.CLIENT, client.get());
                        tempAttr.put(AttributeName.EXPIRY, expiry.get());
                        tempAttr.put(AttributeName.PAYMENT, payment.get());
                        correct = true;
                    }
                }
            }
            if (!correct) {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.ARRANGE_BUY_INCORRECT);
            }
            session.setAttribute(AttributeName.CURRENT_PAGE, PagePath.ARRANGE_BUY);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(PagePath.ARRANGE_BUY);
    }
}
