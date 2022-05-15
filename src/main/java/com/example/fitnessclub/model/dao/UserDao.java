package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean checkingExistence(String login) throws DaoException;
}
