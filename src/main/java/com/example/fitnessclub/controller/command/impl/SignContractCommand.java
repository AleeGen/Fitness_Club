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

public class SignContractCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        HashMap<String, Object> tempAttr = (HashMap<String, Object>) session.getAttribute(AttributeName.TEMP_ATTRIBUTE);
        long trainerId = ((User) tempAttr.get(AttributeName.TRAINER)).getId();
        short totalCost = (short) tempAttr.get(AttributeName.TOTAL_COST);
        String startDate = (String) tempAttr.get(AttributeName.START_DATE);
        String endDate = (String) tempAttr.get(AttributeName.END_DATE);
        String login = (String) session.getAttribute(AttributeName.LOGIN);
        try {
            Optional<User> optionalUser = UserServiceImpl.getInstance().findByLogin(login);
            if (optionalUser.isPresent()) {
                long clientId = optionalUser.get().getId();
                if (ContractServiceImpl.getInstance().add(clientId, trainerId, startDate, endDate, totalCost)) {
                    tempAttr.put(MessagePage.MESSAGE, MessagePage.CONTRACT_SUCCESSFULLY);
                } else {
                    tempAttr.put(MessagePage.MESSAGE, MessagePage.CONTRACT_FAILED);
                }
            } else {
                tempAttr.put(MessagePage.MESSAGE, MessagePage.NOT_FOUND_CLIENT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new CommandException(e);
        }
        session.setAttribute(AttributeName.CURRENT_PAGE, PagePath.INDEX);
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
