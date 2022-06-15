package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.PaymentDao;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao {

    private static PaymentDaoImpl instance = new PaymentDaoImpl();

    private PaymentDaoImpl() {

    }

    public static PaymentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Payment payment) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_USERS_HAS_SERVICES)) {
            statement.setLong(1, payment.getUserId());
            statement.setLong(2, payment.getServiceId());
            statement.setByte(3, payment.getRemainedVisits());
            statement.setBoolean(4, payment.isPaid());
            int execute = statement.executeUpdate();
            if (execute == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean delete(Payment payment) throws DaoException {
        return false;
    }

    @Override
    public List<Payment> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> update(Payment payment) throws DaoException {
        return null;
    }

}
