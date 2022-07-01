package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.AppointmentType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AppointmentMapper implements RowMapper<Appointment> {

    private static final Logger logger = LogManager.getLogger();
    private static final String MAPPING_ERROR = "Mapping error in UserMapper class!";
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
                    .setId(resultSet.getLong(AttributeName.APPOINTMENT_ID))
                    .setUserId(resultSet.getLong(AttributeName.APPOINTMENT_USER_ID))
                    .setDate(resultSet.getDate(AttributeName.DATE))
                    .setNutrition(resultSet.getString(AttributeName.NUTRITION))
                    .setType(AppointmentType.getType(resultSet.getString(AttributeName.APPOINTMENT_TYPE)))
                    .build();
            optionalAppointment = Optional.of(appointment);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
        }
        return optionalAppointment;
    }
}
