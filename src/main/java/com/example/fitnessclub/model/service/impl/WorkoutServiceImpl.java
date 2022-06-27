package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.model.dao.impl.AppointmentDaoImpl;
import com.example.fitnessclub.model.dao.impl.ExerciseDaoImpl;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.Exercise;
import com.example.fitnessclub.model.entity.Workout;
import com.example.fitnessclub.model.service.WorkoutService;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class WorkoutServiceImpl implements WorkoutService {

    private static final Logger logger = LogManager.getLogger();
    private static WorkoutServiceImpl instance = new WorkoutServiceImpl();

    private WorkoutServiceImpl() {
    }

    public static WorkoutServiceImpl getInstance() {  //// TODO: 29.05.2022 сделать потокобезопасным?
        return instance;
    }

    public List<Workout> findAll(long userId) throws ServiceException {
        List<Workout> workouts = new ArrayList<>();
        try {
            List<Appointment> appointments = AppointmentDaoImpl.getInstance().findAll(userId);
            for (var app : appointments) {
                List<Exercise> exerciseList = ExerciseDaoImpl.getInstance().findAll(app.getId());
                workouts.add(new Workout(app, exerciseList));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return workouts;
    }
}
