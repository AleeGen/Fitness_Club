package com.example.fitnessclub.model.service;

import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.ServiceException;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> authenticate(String login, String password) throws ServiceException;

    boolean registration(RequestParameters paramUser) throws ServiceException;

    Optional<User> find(String login) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findTrainers() throws ServiceException;

    Optional<User> update(RequestParameters paramUser) throws ServiceException;

    Optional<User> editFeatures(RequestParameters paramUser) throws ServiceException;

    boolean editAvatar(Part part, String login) throws ServiceException;

    boolean editPassword(RequestParameters parameters) throws ServiceException;

    boolean buy(String login, long paymentId) throws ServiceException;

    boolean blocked(String login, String isBlocked) throws ServiceException;


}
