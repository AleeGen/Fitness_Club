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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao {

    private static final Logger logger = LogManager.getLogger();
    private static PaymentDaoImpl instance = new PaymentDaoImpl();

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
            statement.setByte(3, payment.getRemainedVisits());
            statement.setBoolean(4, payment.isPaid());
            if (statement.executeUpdate() == 1) {
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
    public Optional<Payment> find(String id) throws DaoException {
        return Optional.empty();
    }

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
            throw new DaoException(e);
        }
        return listPayment;
    }

    @Override
    public Optional<Payment> update(Payment payment) throws DaoException {
        Optional<Payment> optionalPayment = Optional.empty();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.UPDATE_PAYMENT);
             PreparedStatement statementSelect = connection.prepareStatement(DatabaseQuery.SELECT_PAYMENT)) {
            connection.setAutoCommit(false);
            statementUpdate.setByte(1, payment.getRemainedVisits());
            statementUpdate.setDate(2, payment.getExpiry());
            statementUpdate.setBoolean(3, payment.isPaid());
            statementUpdate.setLong(4, payment.getId());
            if (statementUpdate.executeUpdate() == 1) {
                statementSelect.setLong(1, payment.getId());
                ResultSet resultSet = statementSelect.executeQuery();
                if (resultSet.next()) {
                    connection.commit();
                    optionalPayment = PaymentMapper.getInstance().rowMap(resultSet);
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.log(Level.ERROR, ex);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return optionalPayment;
    }

    public boolean buy(long paymentId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.BUY_PAYMENT);
             PreparedStatement statementBuy = connection.prepareStatement(DatabaseQuery.MONEY_TRANSFER)) {
            connection.setAutoCommit(false);
            statementUpdate.setLong(1, paymentId);
            if (statementUpdate.executeUpdate() == 1) {
                int result = 1; //statementBuy.executeUpdate(); (stopper)
                if (result == 1) {
                    connection.commit();
                    return true;
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.log(Level.ERROR, ex);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return false;
    }

}
