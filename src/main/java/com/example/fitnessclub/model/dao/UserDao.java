package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean checkingExistence(String login) throws DaoException;

    List<User> findAll() throws DaoException;

    List<User> findTrainers() throws DaoException;

    boolean editFeatures(String login, String role, boolean corporate) throws DaoException;

    boolean editAvatar(String pathAvatar, String login) throws DaoException;

    Optional<String> findPassword(String login) throws DaoException;

    boolean editPassword(String login, String replacePass) throws DaoException;

    boolean blocked(String login, boolean isBlocked) throws DaoException;

    Optional<User> find(long id) throws DaoException;

    boolean plusCash(String login, short cash) throws DaoException;
}
