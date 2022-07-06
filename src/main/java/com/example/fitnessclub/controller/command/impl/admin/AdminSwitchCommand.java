package com.example.fitnessclub.controller.command.impl.admin;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.controller.command.Command;
import com.example.fitnessclub.exception.CommandException;
import com.example.fitnessclub.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AdminSwitchCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(AttributeName.ROLE);
        if (role == UserRole.ADMIN) {
            boolean adminMode = (boolean) session.getAttribute(AttributeName.ADMIN_SWITCH);
            if (adminMode) {
                session.setAttribute(AttributeName.ADMIN_SWITCH, false);
            } else {
                session.setAttribute(AttributeName.ADMIN_SWITCH, true);
            }
        }
        return new Router((String) session.getAttribute(AttributeName.CURRENT_PAGE));
    }
}
