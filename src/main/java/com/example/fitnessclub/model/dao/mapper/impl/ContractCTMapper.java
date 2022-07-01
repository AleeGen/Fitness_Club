package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import com.example.fitnessclub.model.entity.ContractCT;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ContractCTMapper implements RowMapper<ContractCT> {

    private static final Logger logger = LogManager.getLogger();
    private static final String MAPPING_ERROR = "Mapping error in ContractCTMapper class!";
    private static ContractCTMapper instance;

    public static ContractCTMapper getInstance() {
        if (instance == null) {
            instance = new ContractCTMapper();
        }
        return instance;
    }

    private ContractCTMapper() {
    }

    @Override
    public Optional<ContractCT> rowMap(ResultSet resultSet) {
        Optional<ContractCT> optionalContract;
        try {
            ContractCT contract = ContractCT.newBuilder()
                    .setId(resultSet.getLong(AttributeName.SERVICE_ID))
                    .setTotalCost(resultSet.getByte(AttributeName.TOTAL_COST))
                    .setStart(resultSet.getDate(AttributeName.START_DATE))
                    .setEnd(resultSet.getDate(AttributeName.END_DATE))
                    .setUserId(resultSet.getLong(AttributeName.USER_ID))
                    .setTrainerId(resultSet.getLong(AttributeName.TRAINER_ID))
                    .build();
            optionalContract = Optional.of(contract);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalContract = Optional.empty();
        }
        return optionalContract;
    }
}
