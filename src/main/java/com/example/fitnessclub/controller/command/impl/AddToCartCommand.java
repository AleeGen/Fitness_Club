package com.example.fitnessclub.controller.command.impl;


import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddToCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        PaymentServiceImpl paymentService = PaymentServiceImpl.getInstance();
        Long serviceId = Long.valueOf(request.getParameter(AttributeName.SERVICE_ID_CART));
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        if (login != null) {
            if (paymentService.addToCart(login, serviceId)) {
               request.setAttribute(MessagePage.MESSAGE, MessagePage.ADDED_TO_CART);
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.NOT_ADDED_TO_CART);
            }
        }
        String page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        return new Router(page);//// TODO: 11.06.2022 redirect чистит запрос, сообщения не будет
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
