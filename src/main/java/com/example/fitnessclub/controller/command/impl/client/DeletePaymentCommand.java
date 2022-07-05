package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
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

import java.util.HashMap;
import java.util.List;

public class DeletePaymentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final boolean STATUS = false;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String paymentId = request.getParameter(AttributeName.PAYMENT_ID);
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        Router.Type type = Router.Type.FORWARD;
        try {
            if (PaymentServiceImpl.getInstance().delete(paymentId, login)) {
                type = Router.Type.REDIRECT;
                List<Payment> paymentList = PaymentServiceImpl.getInstance().findAll(login, STATUS);
                HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                tempAttr.put(AttributeName.PAYMENTS, paymentList);
                tempAttr.put(AttributeName.PAID, STATUS);
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.DELETE_INCORRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE), type);
    }
}
