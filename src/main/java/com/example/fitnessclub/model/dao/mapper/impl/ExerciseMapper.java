package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import com.example.fitnessclub.model.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExerciseMapper implements RowMapper<Exercise> {

    private static final Logger logger = LogManager.getLogger();
    private static final String MAPPING_ERROR = "Mapping error in UserMapper class!";
    private static ExerciseMapper instance;

    public static ExerciseMapper getInstance() {
        if (instance == null) {
            instance = new ExerciseMapper();
        }
        return instance;
    }

    private ExerciseMapper() {
    }

    @Override
    public Optional<Exercise> rowMap(ResultSet resultSet) {
        Optional<Exercise> optionalExercise = Optional.empty();
        try {
            Exercise exercise = Exercise.newBuilder()
                    .setId(resultSet.getLong(AttributeName.EXERCISE_ID))
                    .setAppointmentId(resultSet.getLong(AttributeName.APPOINTMENT_ID))
                    .setName(resultSet.getString(AttributeName.EXERCISE_NAME))
                    .setNumberSets(resultSet.getByte(AttributeName.NUMBER_SETS))
                    .setNumberRepetitions(resultSet.getByte(AttributeName.NUMBER_REPETITIONS))
                    .setEquipment(resultSet.getString(AttributeName.EQUIPMENT))
                    .setDescription(resultSet.getString(AttributeName.DESCRIPTION))
                    .build();
            optionalExercise = Optional.of(exercise);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
        }
        return optionalExercise;
    }
}
