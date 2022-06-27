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
    public Optional<Appointment> find(String id) throws DaoException {
        return Optional.empty();
    }

    public List<Appointment> findAll(long userId) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_APPOINTMENTS_BY_USER_ID)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Appointment> app = AppointmentMapper.getInstance().rowMap(resultSet);
                    app.ifPresent(appointments::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return appointments;
    }

    @Override
    public Optional<Appointment> update(Appointment appointment) throws DaoException {
        return Optional.empty();
    }
}