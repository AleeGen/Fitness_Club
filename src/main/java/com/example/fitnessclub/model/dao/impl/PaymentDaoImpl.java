package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.PaymentDao;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao {
    @Override
    public boolean add(Payment payment) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_USERS_HAS_SERVICES)) {
            statement.setLong(1, payment.getUserId());
            statement.setLong(2, payment.getServiceId());
            statement.setByte(3, payment.getRemainedVisits());
            statement.setBoolean(4, payment.isPaid());
            System.out.println("add1");
            int execute = statement.executeUpdate();
            System.out.println("add2");
            if (execute == 1) {
                System.out.println("add3");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //log
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
    public Payment update(Payment payment) throws DaoException {
        return null;
    }

}
