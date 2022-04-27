package com.example.fitnessclub.service;

import com.example.fitnessclub.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}
