package com.example.fitnessclub.dao;

import com.example.fitnessclub.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

}
