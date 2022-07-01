package com.example.fitnessclub.controller;

import java.io.*;

import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.command.CommandType;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
        logger.log(Level.INFO, request.getMethod());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
        logger.log(Level.INFO, request.getMethod());
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandStr = request.getParameter(AttributeName.COMMAND);
        Command command = CommandType.define(commandStr);
        try {
            Router router = command.execute(request);
            switch (router.getType()) {
                case FORWARD -> request.getRequestDispatcher(router.getPage()).forward(request, response);
                case REDIRECT -> response.sendRedirect(router.getPage());
                default -> {
                    request.setAttribute(MessagePage.MESSAGE, MessagePage.UNKNOWN_TRANSITION_ROUTER);
                    request.getRequestDispatcher(PagePath.ERROR_500).forward(request, response);
                }
            }
        } catch (CommandException e) {
            request.setAttribute(MessagePage.MESSAGE, e);
            request.getRequestDispatcher(PagePath.ERROR_500).forward(request, response);
        }
    }
}