package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.AppointmentType;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AppointmentMapper implements RowMapper {

    private static final Logger logger = LogManager.getLogger();

    private final String MAPPING_ERROR = "Mapping error in UserMapper class!";
    private static AppointmentMapper instance;

    public static AppointmentMapper getInstance() {
        if (instance == null) {
            instance = new AppointmentMapper();
        }
        return instance;
    }

    private AppointmentMapper() {
    }

    @Override
    public Optional<Appointment> rowMap(ResultSet resultSet) {
        Optional<Appointment> optionalAppointment = Optional.empty();
        try {
                Appointment appointment = Appointment.newBuilder()
                        .setId(resultSet.getLong(AttributeName.APPOINTMENT_DESCRIPTION_ID))
                        .setAppointmentId(resultSet.getLong(AttributeName.APPOINTMENT_ID))
                        //.setDate(resultSet.getDate(AttributeName.DATE))
                        //.setType(AppointmentType.getType(resultSet.getString(AttributeName.APPOINTMENT_TYPE)))
                        .setExerciseName(resultSet.getString(AttributeName.EXERCISE_NAME))
                        .setNumberSets(resultSet.getByte(AttributeName.NUMBER_SETS))
                        .setNumberRepetitions(resultSet.getByte(AttributeName.NUMBER_REPETITIONS))
                        .setEquipment(resultSet.getString(AttributeName.EQUIPMENT))
                        .setNutrition(resultSet.getString(AttributeName.NUTRITION))
                        .setRunTime(resultSet.getTime(AttributeName.RUN_TIME))
                        .build();
                optionalAppointment = Optional.of(appointment);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
        }
        return optionalAppointment;
    }
}
