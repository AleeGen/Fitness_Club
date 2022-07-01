package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.ContractDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.mapper.impl.ContractCTMapper;
import com.example.fitnessclub.model.entity.ContractCT;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContractDaoImpl extends BaseDao<ContractCT> implements ContractDao {

    private static final ContractDaoImpl instance = new ContractDaoImpl();

    private ContractDaoImpl() {
    }

    public static ContractDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(ContractCT contractCT) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(String contractId) throws DaoException {
        return false;
    }

    @Override
    public Optional<ContractCT> find(String id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<ContractCT> update(ContractCT contractCT) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<ContractCT> findAll(long trainerId) throws DaoException {
        List<ContractCT> listContract = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_CONTRACT_BY_CLIENT_TRAINER)) {
            statement.setLong(1, trainerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<ContractCT> optionalContractCT = ContractCTMapper.getInstance().rowMap(resultSet);
                    optionalContractCT.ifPresent(listContract::add);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return listContract;
    }
}
