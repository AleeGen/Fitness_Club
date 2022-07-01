package com.example.fitnessclub.controller.command;

import com.example.fitnessclub.controller.Router;
import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
