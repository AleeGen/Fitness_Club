package com.example.fitnessclub.command.impl;

import com.example.fitnessclub.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    private static String INDEX = "index.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        return INDEX;
    }
}
