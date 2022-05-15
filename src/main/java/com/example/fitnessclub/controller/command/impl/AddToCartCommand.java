package com.example.fitnessclub.controller.command.impl;


import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Item;
import com.example.fitnessclub.model.service.PaymentService;
import com.example.fitnessclub.model.service.impl.ItemServiceImpl;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class AddToCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        PaymentServiceImpl paymentService = PaymentServiceImpl.getInstance();
        Long serviceId = Long.valueOf(request.getParameter(AttributeName.SERVICE_CART));
        Long userId = (Long) request.getSession().getAttribute(AttributeName.USER_ID);
        System.out.println("step1");
        if (paymentService.addToCart(userId, serviceId)) {
            request.setAttribute(MessagePage.MESSAGE, MessagePage.ADDED_TO_CART);
        } else {
            request.setAttribute(MessagePage.MESSAGE, MessagePage.NOT_ADDED_TO_CART);
        }
        try {
            List<Item> services = ItemServiceImpl.getInstance().findAll();
            request.setAttribute(AttributeName.SERVICES, services);
        } catch (ServiceException e) {
            //log
            throw new CommandException(e);
        }
        String page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        return new Router(page);
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
