package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.ExerciseDao;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.mapper.impl.ExerciseMapper;
import com.example.fitnessclub.model.entity.Exercise;
import com.example.fitnessclub.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExerciseDaoImpl extends BaseDao<Exercise> implements ExerciseDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ExerciseDaoImpl instance = new ExerciseDaoImpl();

    private ExerciseDaoImpl() {
    }

    public static ExerciseDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Exercise exercise) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_EXERCISE)) {
            statement.setString(1, exercise.getName());
            statement.setByte(2, exercise.getNumberSets());
            statement.setByte(3, exercise.getNumberRepetitions());
            statement.setString(4, exercise.getEquipment());
            statement.setString(5, exercise.getDescription());
            statement.setLong(6, exercise.getAppointmentId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to add exercise with appointment_id = " + exercise.getAppointmentId());
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(String exerciseId) throws DaoException {
        return false;
    }

    @Override
    public Optional<Exercise> find(String id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Exercise> findAll(long appointmentId) throws DaoException {
        List<Exercise> listExercise = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_EXERCISE_BY_APP_ID)) {
            statement.setLong(1, appointmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Exercise> exercise = ExerciseMapper.getInstance().rowMap(resultSet);
                    exercise.ifPresent(listExercise::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find exercises with appointment_id = " + appointmentId);
            throw new DaoException(e);
        }
        return listExercise;
    }

    @Override
    public Optional<Exercise> update(Exercise exercise) throws DaoException {
        Optional<Exercise> optionalExercise = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.UPDATE_EXERCISE)) {
            statementUpdate.setString(1, exercise.getName());
            statementUpdate.setByte(2, exercise.getNumberSets());
            statementUpdate.setByte(3, exercise.getNumberRepetitions());
            statementUpdate.setString(4, exercise.getEquipment());
            statementUpdate.setString(5, exercise.getDescription());
            statementUpdate.setLong(6, exercise.getId());
            if (statementUpdate.executeUpdate() == 1) {
                try (PreparedStatement statementSelect = connection.prepareStatement(DatabaseQuery.SELECT_EXERCISE)) {
                    statementSelect.setLong(1, exercise.getAppointmentId());
                    try (ResultSet resultSet = statementSelect.executeQuery()) {
                        if (resultSet.next()) {
                            optionalExercise = ExerciseMapper.getInstance().rowMap(resultSet);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update exercises with exercise_id = " + exercise.getId());
            throw new DaoException(e);
        }
        return optionalExercise;
    }
}