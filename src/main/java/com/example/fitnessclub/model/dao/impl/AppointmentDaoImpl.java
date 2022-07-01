package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.AppointmentDao;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.mapper.impl.AppointmentMapper;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDaoImpl extends BaseDao<Appointment> implements AppointmentDao {

    private static final AppointmentDaoImpl instance = new AppointmentDaoImpl();

    private AppointmentDaoImpl() {
    }

    public static AppointmentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Appointment appointment) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_APPOINTMENT)) {
            statement.setDate(1, appointment.getDate());
            statement.setString(2, appointment.getType().name().toLowerCase());
            statement.setLong(3, appointment.getUserId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(String appointmentId) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.DELETE_APPOINTMENT)) {
            statement.setLong(1, Long.parseLong(appointmentId));
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<Appointment> find(String id) throws DaoException {
        Optional<Appointment> optionalAppointment = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_APPOINTMENT)) {
            statement.setLong(1, Long.parseLong(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalAppointment = AppointmentMapper.getInstance().rowMap(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalAppointment;
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
            throw new DaoException(e);
        }
        return appointments;
    }

    @Override
    public Optional<Appointment> update(Appointment appointment) throws DaoException {
        Optional<Appointment> optionalAppointment = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.UPDATE_APPOINTMENT)) {
            statementUpdate.setDate(1, appointment.getDate());
            statementUpdate.setString(2, appointment.getType().name().toLowerCase());
            statementUpdate.setString(3, appointment.getNutrition());
            statementUpdate.setLong(4, appointment.getId());
            if (statementUpdate.executeUpdate() == 1) {
                try (PreparedStatement statementSelect = connection.prepareStatement(DatabaseQuery.SELECT_APPOINTMENT)) {
                    statementSelect.setLong(1, appointment.getId());
                    try (ResultSet resultSet = statementSelect.executeQuery()) {
                        if (resultSet.next()) {
                            optionalAppointment = AppointmentMapper.getInstance().rowMap(resultSet);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalAppointment;
    }
}