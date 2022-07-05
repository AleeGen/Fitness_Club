package com.example.fitnessclub.controller.command.impl.client;

import com.example.fitnessclub.controller.PagePath;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
