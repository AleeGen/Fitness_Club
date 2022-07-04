package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.ContractDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.mapper.impl.ContractCTMapper;
import com.example.fitnessclub.model.entity.ContractCT;
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

public class ContractDaoImpl extends BaseDao<ContractCT> implements ContractDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ContractDaoImpl instance = new ContractDaoImpl();
    private static final byte SERVICE_ID = 10;
    private static final short REMAINED_VISITS_MAX = 255;
    private static final boolean PAID = true;

    private ContractDaoImpl() {
    }

    public static ContractDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(ContractCT contractCT) throws DaoException {
        boolean result = false;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statementContract = connection.prepareStatement(DatabaseQuery.INSERT_CONTRACT);
             PreparedStatement statementUser = connection.prepareStatement(DatabaseQuery.MINUS_CASH);
             PreparedStatement statementPayment = connection.prepareStatement(DatabaseQuery.INSERT_PAYMENT_PAID)) {
            connection.setAutoCommit(false);
            statementContract.setLong(1, contractCT.getUserId());
            statementContract.setLong(2, contractCT.getTrainerId());
            statementContract.setShort(3, contractCT.getTotalCost());
            statementContract.setDate(4, contractCT.getStart());
            statementContract.setDate(5, contractCT.getEnd());
            if (statementContract.executeUpdate() == 1) {
                statementPayment.setLong(1, contractCT.getUserId());
                statementPayment.setLong(2, SERVICE_ID);
                statementPayment.setShort(3, REMAINED_VISITS_MAX);
                statementPayment.setDate(4, contractCT.getEnd());
                statementPayment.setBoolean(5, PAID);
                if (statementPayment.executeUpdate() == 1) {
                    statementUser.setShort(1, contractCT.getTotalCost());
                    statementUser.setLong(2, contractCT.getUserId());
                    result = statementUser.executeUpdate() == 1;
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } else {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.log(Level.ERROR, e);
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
        return result;
    }

    @Override
    public boolean delete(String contractId) throws DaoException {
        return false;
    }

    @Override
    public Optional<ContractCT> find(String clientId) throws DaoException {
        Optional<ContractCT> contract = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_CONTRACT_BY_CLIENT)) {
            statement.setLong(1, Long.parseLong(clientId));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    contract = ContractCTMapper.getInstance().rowMap(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contract;
    }

    @Override
    public Optional<ContractCT> update(ContractCT contractCT) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<ContractCT> findAll(long trainerId) throws DaoException {
        List<ContractCT> contracts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_CONTRACTS_BY_TRAINER)) {
            statement.setLong(1, trainerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<ContractCT> contract = ContractCTMapper.getInstance().rowMap(resultSet);
                    contract.ifPresent(contracts::add);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contracts;
    }
}
