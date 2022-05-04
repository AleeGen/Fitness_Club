package com.example.fitnessclub.command;

import com.example.fitnessclub.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

@FunctionalInterface
public interface Command {
    //// TODO: 17.04.2022 вынести в константы в отдельный класс как параметры request 
    //// TODO: 27.04.2022 валидацию и прочее подобное выполнять в сервисах, но не в командах 
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
