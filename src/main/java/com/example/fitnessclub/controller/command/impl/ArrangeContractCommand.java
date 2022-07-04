package com.example.fitnessclub.controller.command.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.impl.ContractServiceImpl;
import com.example.fitnessclub.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Optional;

public class ArrangeContractCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        String startDate = request.getParameter(AttributeName.START_DATE);
        String endDate = request.getParameter(AttributeName.END_DATE);
        String trainerId = request.getParameter(AttributeName.TRAINER_ID);
        try {
            Optional<Short> cost = ContractServiceImpl.getInstance().calculate(startDate, endDate, login);
            if (cost.isPresent()) {
                HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
                Optional<User> client = UserServiceImpl.getInstance().findByLogin(login);
                Optional<User> trainer = UserServiceImpl.getInstance().findById(trainerId);
                if (client.isPresent() && trainer.isPresent()) {
                    tempAttr.put(AttributeName.TOTAL_COST, cost.get());
                    tempAttr.put(AttributeName.CLIENT, client.get());
                    tempAttr.put(AttributeName.TRAINER, trainer.get());
                    tempAttr.put(AttributeName.START_DATE, startDate);
                    tempAttr.put(AttributeName.END_DATE, endDate);
                }
            } else {
                request.setAttribute(MessagePage.MESSAGE, MessagePage.INCORRECT_ARRANGE_CONTRACT);
            }
            session.setAttribute(AttributeName.CURRENT_PAGE, PagePath.ARRANGE_CONTRACT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        return new Router(PagePath.ARRANGE_CONTRACT);
    }
}
