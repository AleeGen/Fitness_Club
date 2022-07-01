package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

public class AddToCartCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        PaymentServiceImpl paymentService = PaymentServiceImpl.getInstance();
        Long serviceId = Long.valueOf(request.getParameter(AttributeName.SERVICE_ID_CART));
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        if (login != null) {
            try {
                HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                if (paymentService.addToCart(login, serviceId)) {
                    tempAttr.put(MessagePage.MESSAGE, MessagePage.ADDED_TO_CART);
                } else {
                    tempAttr.put(MessagePage.MESSAGE, MessagePage.NOT_ADDED_TO_CART);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                throw new CommandException(e);
            }
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE), Router.Type.REDIRECT);
    }
}
