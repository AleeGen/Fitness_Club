package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
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

import java.time.LocalDate;
import java.util.HashMap;

public class BuyCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
        long paymentId = ((Payment) tempAttr.get(AttributeName.PAYMENT)).getId();
        LocalDate expiry = (LocalDate) tempAttr.get(AttributeName.EXPIRY);
        short cost = (short) tempAttr.get(AttributeName.COST);
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        try {
            if (PaymentServiceImpl.getInstance().buy(login, paymentId, expiry, cost)) {
                tempAttr.put(MessagePage.MESSAGE, MessagePage.BUY_SUCCESSFULLY);
            } else {
                tempAttr.put(MessagePage.MESSAGE, MessagePage.BUY_NOT_SUCCESSFULLY);
            }
        } catch (
                ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
