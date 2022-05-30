package com.example.fitnessclub.controller.command.impl;


import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.ParameterName;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddToCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        PaymentServiceImpl paymentService = PaymentServiceImpl.getInstance();
        Long serviceId = Long.valueOf(request.getParameter(ParameterName.SERVICE_CART));
        Long userId = (Long) request.getSession().getAttribute(AttributeName.USER_ID);
        if (paymentService.addToCart(userId, serviceId)) {
            request.setAttribute(MessagePage.MESSAGE, MessagePage.ADDED_TO_CART);
        } else {
            request.setAttribute(MessagePage.MESSAGE, MessagePage.NOT_ADDED_TO_CART);
        }
        return new Router((String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE));
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
