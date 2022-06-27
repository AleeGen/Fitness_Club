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
    private static ExerciseDaoImpl instance = new ExerciseDaoImpl();

    private ExerciseDaoImpl() {
    }

    public static ExerciseDaoImpl getInstance() {
        return instance;
    }


    @Override
    public boolean add(Exercise exercise) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Exercise exercise) throws DaoException {
        return false;
    }

    @Override
    public Optional<Exercise> find(String id) throws DaoException {
        return Optional.empty();
    }

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
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return listExercise;
    }

    @Override
    public Optional<Exercise> update(Exercise exercise) throws DaoException {
        return Optional.empty();
    }
}