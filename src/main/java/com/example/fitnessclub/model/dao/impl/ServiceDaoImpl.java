package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.ServiceDao;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.dao.mapper.impl.ServiceMapper;
import com.example.fitnessclub.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceDaoImpl extends BaseDao<Service> implements ServiceDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ServiceDaoImpl instance = new ServiceDaoImpl();

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
    public boolean delete(String itemId) throws DaoException {
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
            logger.log(Level.ERROR, "Failed to find all services");
            throw new DaoException(e);
        }
        return services;
    }

    @Override
    public Optional<Service> update(Service item) throws DaoException {
        return Optional.empty();
    }

    @Override
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
            logger.log(Level.ERROR, "Failed to find service with service_id = " + serviceId);
            throw new DaoException(e);
        }
        return item;
    }

    @Override
    public Optional<Byte> takePrice(long id) throws DaoException {
        Optional<Byte> price = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_SERVICE_PRICE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    price = Optional.of(resultSet.getByte(AttributeName.PRICE));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to takePrice service with service_id = " + id);
            throw new DaoException(e);
        }
        return price;
    }
}
