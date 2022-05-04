package com.example.fitnessclub.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.example.fitnessclub.command.Command;
import com.example.fitnessclub.command.CommandType;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.pool.ConnectionPool;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


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

    public void init() {
        Enumeration e = this.getServletConfig().getInitParameterNames();
        while (e.hasMoreElements()) { //// TODO: 27.04.2022 записываю в map параметры инициализации. Зачем? возможно понадобиться (passAdmin - admin, passUser - user)
            String name = (String) e.nextElement();
            String value = this.getServletConfig().getInitParameter(name);
            paramInit.put(name, value);
        }
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "|||++++++++++> servlet init: " + this.getServletInfo());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("->11");
        logger.log(Level.INFO, "first log from" + this.getServletName() + " Method " + request.getMethod());
        response.setContentType("text/html");//// TODO: 19.04.2022 что за строка?  это фильтр! а что он делает?
        //response.addCookie() добавляет куки к объекту ответа для пересылки на клиентский компьютер (может полезно будет это использовать?)
        System.out.println("->12");
        String commandStr = request.getParameter("command");
        System.out.println("->13");
        // параметры реквеста - это та информация, которая передается от форм jsp
        // а атрибуты реквеста, это та информация, которую мы сами устанавливаем методом setAttribute()
        Command command = CommandType.define(commandStr);
        System.out.println("->14");
        String page = null;
        try {
            page = command.execute(request);
            System.out.println("->15");
            request.getRequestDispatcher(page).forward(request, response);
            System.out.println("->16");
            //response.sendRedirect(page); //// TODO: 23.04.2022 redirect изначально переходит в корень page, поэтому работает некорректно
            // // TODO: 23.04.2022 redirect используем такой переход для защиты от f5 
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }


    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "|||++++++++++> servlet destroyed: " + this.getServletName());
    }
}