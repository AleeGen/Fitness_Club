package com.example.fitnessclub.controller;

import java.io.*;
import java.util.*;
import java.util.function.Function;

import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.command.CommandType;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Node;


@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"},
        initParams = {
                @WebInitParam(name = "passAdmin", value = "admin"),
                @WebInitParam(name = "passUser", value = "user")})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final String SAVE_PATH = "D:\\dir\\";
    private static final String UPLOAD_DIR = "uploads";
    private static Map<String, String> paramInit = new HashMap<String, String>();

    //// TODO: 16.04.2022 загрузка parts происходит вместе с загрузкой кнопки submit
    public void init() {
        /*Enumeration e = this.getServletConfig().getInitParameterNames();
        while (e.hasMoreElements()) { //// TODO: 27.04.2022 записываю в map параметры инициализации. Зачем? возможно понадобиться (passAdmin - admin, passUser - user)
            String name = (String) e.nextElement();
            String value = this.getServletConfig().getInitParameter(name);
            paramInit.put(name, value);
        }*/
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "servlet init: " + this.getServletInfo());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathApp = req.getServletContext().getRealPath("");
        String uploadDir = pathApp + File.separator + UPLOAD_DIR + File.separator;

        File fileSaveDir = new File(uploadDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        req.getParts()
                .stream()
                .forEach(part -> {
                    String name = part.getSubmittedFileName();
                    try {
                        part.write(uploadDir + name);
                        req.setAttribute("upload_result", name + "upload successfully");
                    } catch (IOException e) {
                        e.printStackTrace();
                        req.setAttribute("upload_result", name + " upload failed");
                    }
                });
        req.getRequestDispatcher("pages/main.jsp").forward(req, resp);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.log(Level.INFO, "first log from " + this.getServletName() + " Method " + request.getMethod());
        response.setContentType("text/html"); //// TODO: 19.04.2022 что за строка?  это фильтр! а что он делает?
        System.out.println("-----------------------------");
        String commandStr = request.getParameter(ParameterName.COMMAND);
        Command command = CommandType.define(commandStr);
        try {
            Router router = command.execute(request);
            switch (router.getType()) {
                case FORWARD:
                    System.out.println("Forward->");
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    System.out.println("Redirect->");
                    response.sendRedirect(router.getPage());
                    break;
                default:
                    response.sendError(500, MessagePage.UNKNOWN_TRANSITION_ROUTER);
            }
            //// TODO: 23.04.2022 redirect изначально переходит в корень page, поэтому работает некорректно
            //// TODO: 23.04.2022 redirect используем такой переход для защиты от f5
        } catch (CommandException e) {
            response.sendError(500, e.getMessage());
        }
        System.out.println("-----------------------------");
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "servlet destroyed: " + this.getServletName());
    }
}