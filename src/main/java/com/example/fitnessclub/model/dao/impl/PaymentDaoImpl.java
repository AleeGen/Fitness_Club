package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.PaymentDao;
import com.example.fitnessclub.model.dao.mapper.impl.PaymentMapper;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao {

    private static final Logger logger = LogManager.getLogger();
    private static final PaymentDaoImpl instance = new PaymentDaoImpl();

    private PaymentDaoImpl() {
    }

    public static PaymentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Payment payment) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_PAYMENT)) {
            statement.setLong(1, payment.getUserId());
            statement.setLong(2, payment.getServiceId());
            statement.setShort(3, payment.getRemainedVisits());
            statement.setBoolean(4, payment.isPaid());
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to add payment with user_id = " + payment.getUserId());
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean delete(String paymentId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.DELETE_PAYMENT)) {
            statement.setLong(1, Long.parseLong(paymentId));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete payment with payment_id = " + paymentId);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Payment> find(String id) throws DaoException {
        Optional<Payment> optionalPayment = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_PAYMENT)) {
            statement.setLong(1, Long.parseLong(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalPayment = PaymentMapper.getInstance().rowMap(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find payment with payment_id = " + id);
            throw new DaoException(e);
        }
        return optionalPayment;
    }

    @Override
    public List<Payment> findAll(long userId) throws DaoException {
        List<Payment> listPayment = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_PAYMENT)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Payment> payment = PaymentMapper.getInstance().rowMap(resultSet);
                    payment.ifPresent(listPayment::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find payments with user_id = " + userId);
            throw new DaoException(e);
        }
        return listPayment;
    }

    @Override
    public Optional<Payment> update(Payment payment) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean buy(long userId, long paymentId, Date exercise, short cost, short countDay) throws DaoException {
        boolean result = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.UPDATE_BUY_PAYMENT);
             PreparedStatement statementBuy = connection.prepareStatement(DatabaseQuery.UPDATE_CASH_AND_DAYS)) {
            connection.setAutoCommit(false);
            statementUpdate.setDate(1, exercise);
            statementUpdate.setLong(2, paymentId);
            if (statementUpdate.executeUpdate() == 1) {
                statementBuy.setShort(1, cost);
                statementBuy.setShort(2, countDay);
                statementBuy.setLong(3, userId);
                if (statementBuy.executeUpdate() == 1) {
                    connection.commit();
                    connection.setAutoCommit(true);
                    result = true;
                }
            }
            if (!result) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.log(Level.ERROR, ex);
            }
            logger.log(Level.ERROR, "Failed to transaction buy payment for user_id =" + userId);
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return result;
    }
}
