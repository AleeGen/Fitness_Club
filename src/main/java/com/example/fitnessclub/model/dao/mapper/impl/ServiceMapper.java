package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import com.example.fitnessclub.model.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ServiceMapper implements RowMapper<Service> {

    private static final Logger logger = LogManager.getLogger();

    private final String MAPPING_ERROR = "Mapping error in ItemMapper class!";
    private static ServiceMapper instance;

    public static ServiceMapper getInstance() {
        if (instance == null) {
            instance = new ServiceMapper();
        }
        return instance;
    }

    private ServiceMapper() {
    }

    @Override
    public Optional<Service> rowMap(ResultSet resultSet) {
        Optional<Service> optionalService;
        try {
            Service service = Service.newBuilder()
                    .setId(resultSet.getLong(AttributeName.SERVICE_ID))
                    .setServiceName(resultSet.getString(AttributeName.SERVICE_NAME))
                    .setNumberVisit(resultSet.getByte(AttributeName.NUMBER_VISITS))
                    .setValidityPeriod(resultSet.getString(AttributeName.VALIDITY_PERIOD))
                    .setPrice(resultSet.getInt(AttributeName.PRICE))
                    .setDescription(resultSet.getString(AttributeName.DESCRIPTION))
                    .build();
            optionalService = Optional.of(service);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalService = Optional.empty();
        }
        return optionalService;
    }
}
