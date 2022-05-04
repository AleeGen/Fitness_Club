package com.example.fitnessclub.command.impl;

import com.example.fitnessclub.command.Command;
import com.example.fitnessclub.constants.MassagePage;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class OpenPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        System.out.println("openPageCommand");
        String page = request.getParameter("page");
        System.out.println(page);
        return page;
    }

    @Override
    public void refresh() {
        Command.super.refresh();
    }
}
