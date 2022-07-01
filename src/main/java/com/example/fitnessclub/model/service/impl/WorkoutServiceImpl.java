package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.dao.impl.AppointmentDaoImpl;
import com.example.fitnessclub.model.dao.impl.ContractDaoImpl;
import com.example.fitnessclub.model.dao.impl.ExerciseDaoImpl;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
import com.example.fitnessclub.model.entity.*;
import com.example.fitnessclub.model.service.WorkoutService;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class WorkoutServiceImpl implements WorkoutService {

    private static final WorkoutServiceImpl instance = new WorkoutServiceImpl();
    private static final Date DEFAULT_DATE = Date.valueOf(LocalDate.now());
    private static final AppointmentType DEFAULT_TYPE = AppointmentType.FITNESS_ROOM;

    private WorkoutServiceImpl() {
    }

    public static WorkoutServiceImpl getInstance() {
        return instance;
    }

    @Override
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

    @Override
    public Optional<Workout> find(String appointmentId) throws ServiceException {
        return supportFind(appointmentId);
    }

    @Override
    public boolean canUserChange(String appointmentId, String login) throws ServiceException {
        try {
            Optional<User> optionalUser = UserDaoImpl.getInstance().find(login);
            if (optionalUser.isPresent()) {
                long userId = optionalUser.get().getId();
                Optional<Appointment> optionalAppointment = AppointmentDaoImpl.getInstance().find(appointmentId);
                if (optionalAppointment.isPresent()) {
                    long neededId = optionalAppointment.get().getUserId();
                    if (userId == neededId) {
                        return true;
                    } else {
                        return supportRelevant(userId, neededId);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    private boolean supportRelevant(long userId, long neededId) throws DaoException {
        List<ContractCT> listContract = ContractDaoImpl.getInstance().findAll(userId);
        for (var contract : listContract) {
            if (contract.getUserId() == neededId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canUserAdd(String loginToChange, String login) throws ServiceException {
        if (loginToChange.equals(login)) {
            return true;
        }
        boolean result = false;
        try {
            Optional<User> user = UserDaoImpl.getInstance().find(login);
            Optional<User> userChange = UserDaoImpl.getInstance().find(loginToChange);
            if (user.isPresent() && userChange.isPresent()) {
                long userId = user.get().getId();
                long userIdChange = user.get().getId();
                result = supportRelevant(userId, userIdChange);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<Workout> update(RequestParameters workoutParam) throws ServiceException {
        Optional<Workout> optionalWorkout;
        String date = workoutParam.get(AttributeName.DATE);
        Date resultDate = date.isBlank() ? null : Date.valueOf(date);
        String appointmentId = workoutParam.get(AttributeName.APPOINTMENT_ID);
        Appointment appointment = Appointment.newBuilder()
                .setId(Long.valueOf(appointmentId))
                .setDate(resultDate)
                .setType(AppointmentType.getType(workoutParam.get(AttributeName.APPOINTMENT_TYPE)))
                .setNutrition(workoutParam.get(AttributeName.NUTRITION))
                .build();
        int count = workoutParam.getMas(AttributeName.EXERCISE_ID).length;
        try {
            AppointmentDaoImpl.getInstance().update(appointment);
            for (int i = 0; i < count; i++) {
                String id = workoutParam.getMas(AttributeName.EXERCISE_ID)[i];
                String name = workoutParam.getMas(AttributeName.NAME)[i];
                String numberSets = workoutParam.getMas(AttributeName.NUMBER_SETS)[i];
                String numberRepetitions = workoutParam.getMas(AttributeName.NUMBER_REPETITIONS)[i];
                String equipment = workoutParam.getMas(AttributeName.EQUIPMENT)[i];
                String description = workoutParam.getMas(AttributeName.DESCRIPTION)[i];
                if (id.isBlank()) {
                    Exercise exercise = Exercise.newBuilder()
                            .setName(name)
                            .setNumberSets(Byte.parseByte(numberSets))
                            .setNumberRepetitions(Byte.parseByte(numberRepetitions))
                            .setEquipment(equipment)
                            .setDescription(description)
                            .setAppointmentId(Long.parseLong(appointmentId))
                            .build();
                    ExerciseDaoImpl.getInstance().add(exercise);
                } else {
                    Exercise exercise = Exercise.newBuilder()
                            .setId(Long.valueOf(id))
                            .setName(name)
                            .setNumberSets(Byte.parseByte(numberSets))
                            .setNumberRepetitions(Byte.parseByte(numberRepetitions))
                            .setEquipment(equipment)
                            .setDescription(description)
                            .setAppointmentId(Long.parseLong(appointmentId))
                            .build();
                    ExerciseDaoImpl.getInstance().update(exercise);
                }
            }
            optionalWorkout = supportFind(appointmentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalWorkout;
    }

    private Optional<Workout> supportFind(String appointmentId) throws ServiceException {
        Optional<Workout> workout = Optional.empty();
        try {
            Optional<Appointment> optionalAppointment = AppointmentDaoImpl.getInstance().find(appointmentId);
            if (optionalAppointment.isPresent()) {
                Appointment appointment = optionalAppointment.get();
                List<Exercise> exercises = ExerciseDaoImpl.getInstance().findAll(appointment.getId());
                workout = Optional.of(new Workout(appointment, exercises));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return workout;
    }

    @Override
    public boolean delete(String appointmentId) throws ServiceException {
        try {
            return AppointmentDaoImpl.getInstance().delete(appointmentId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Workout> add(String login) throws ServiceException {
        List<Workout> workout = new ArrayList<>();
        try {
            Optional<User> optionalUser = UserDaoImpl.getInstance().find(login);
            if (optionalUser.isPresent()) {
                long userId = optionalUser.get().getId();
                Appointment appointment = Appointment.newBuilder()
                        .setDate(DEFAULT_DATE)
                        .setType(DEFAULT_TYPE)
                        .setUserId(userId)
                        .build();
                if (AppointmentDaoImpl.getInstance().add(appointment)) {
                    workout = supportFindAll(userId);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return workout;
    }

    private List<Workout> supportFindAll(long userId) throws DaoException {
        List<Workout> workouts = new ArrayList<>();
        List<Appointment> appointments = AppointmentDaoImpl.getInstance().findAll(userId);
        for (var app : appointments) {
            List<Exercise> exerciseList = ExerciseDaoImpl.getInstance().findAll(app.getId());
            workouts.add(new Workout(app, exerciseList));
        }
        return workouts;
    }
}
