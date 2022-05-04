package com.example.fitnessclub.service;

import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;

    boolean registration(RequestParameters paramUser) throws ServiceException;
}
