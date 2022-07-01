package com.example.fitnessclub.model.service;

import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {

    List<Workout> findAll(long userId) throws ServiceException;

    Optional<Workout> find(String appointmentId) throws ServiceException;

    boolean canUserChange(String appointmentId, String login) throws ServiceException;

    boolean canUserAdd(String loginToChange, String login) throws ServiceException;

    Optional<Workout> update(RequestParameters workoutParam) throws ServiceException;

    boolean delete(String appointmentId) throws ServiceException;

    List<Workout> add(String login) throws ServiceException;
}
