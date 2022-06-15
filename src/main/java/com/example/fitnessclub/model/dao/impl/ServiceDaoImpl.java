package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.ServiceDao;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.dao.mapper.impl.ServiceMapper;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceDaoImpl extends BaseDao<Service> implements ServiceDao {

    private static ServiceDaoImpl instance = new ServiceDaoImpl();

    private ServiceDaoImpl() {

    }

    public static ServiceDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Service item) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Service item) throws DaoException {
        return false;
    }

    @Override
    public List<Service> findAll() throws DaoException {
        List<Service> services = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_SERVICES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<Service> service = ServiceMapper.getInstance().rowMap(resultSet);
                service.ifPresent(services::add);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return services;
    }

    @Override
    public Optional<User> update(Service item) throws DaoException {
        return null;
    }

    public Optional<Service> find(String serviceId) throws DaoException {
        Optional<Service> item = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_SERVICE_BY_ID)) {
            statement.setString(1, serviceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = ServiceMapper.getInstance().rowMap(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return item;
    }
}
