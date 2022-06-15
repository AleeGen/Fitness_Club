package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.AppointmentDao;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.mapper.impl.AppointmentMapper;
import com.example.fitnessclub.model.dao.mapper.impl.ServiceMapper;
import com.example.fitnessclub.model.dao.mapper.impl.UserMapper;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.entity.AppointmentType;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.entity.User;
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

public class AppointmentDaoImpl extends BaseDao<Appointment> implements AppointmentDao {

    private static final Logger logger = LogManager.getLogger();
    private static AppointmentDaoImpl instance = new AppointmentDaoImpl();

    private AppointmentDaoImpl() {
    }

    public static AppointmentDaoImpl getInstance() {
        return instance;
    }


    @Override
    public boolean add(Appointment appointment) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Appointment appointment) throws DaoException {
        return false;
    }

    @Override
    public List<Appointment> findAll() throws DaoException {
        return null;
    }

    public List<Appointment> findAll(long userId) throws DaoException {
        logger.log(Level.INFO,"1");
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementApp = connection.prepareStatement(DatabaseQuery.SELECT_APPOINTMENTS_BY_USER_ID)) {
            logger.log(Level.INFO,"2");
            statementApp.setLong(1, userId);
            try (ResultSet resultSetApp = statementApp.executeQuery()) {
                logger.log(Level.INFO,"3");
                while (resultSetApp.next()) {
                    logger.log(Level.INFO,"4");
                    try (PreparedStatement statementAppDescription = connection.prepareStatement(DatabaseQuery.SELECT_APPOINTMENTS_DESCRIPTION_BY_ID)) {
                        statementAppDescription.setLong(1, resultSetApp.getLong(AttributeName.APPOINTMENT_ID));
                        try (ResultSet resultSetAppDescription = statementAppDescription.executeQuery()) {
                            while (resultSetAppDescription.next()) {
                                logger.log(Level.INFO,"5");
                                logger.log(Level.INFO,resultSetAppDescription.getString(AttributeName.EXERCISE_NAME));
                                Optional<Appointment> appointment = AppointmentMapper.getInstance().rowMap(resultSetAppDescription);
                                if (appointment.isPresent()) {
                                    logger.log(Level.INFO,appointment.get().getExerciseName());
                                    logger.log(Level.INFO,"6");
                                    Appointment app = appointment.get();
                                    app.setDate(resultSetApp.getDate(AttributeName.DATE));
                                    AppointmentType type = AppointmentType.getType(resultSetApp.getString(AttributeName.APPOINTMENT_TYPE));
                                    app.setType(type);
                                    appointments.add(app);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        logger.log(Level.INFO,"7");
        return appointments;
    }

    @Override
    public Optional<User> update(Appointment appointment) throws DaoException {
        return Optional.empty();
    }
}