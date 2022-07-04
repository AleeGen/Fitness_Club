package com.example.fitnessclub.model.dao.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.dao.mapper.RowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentMapper implements RowMapper<Payment> {

    private static final Logger logger = LogManager.getLogger();
    private static final String MAPPING_ERROR = "Mapping error in PaymentMapper class!";
    private static PaymentMapper instance;

    public static PaymentMapper getInstance() {
        if (instance == null) {
            instance = new PaymentMapper();
        }
        return instance;
    }

    private PaymentMapper() {

    }

    @Override
    public Optional<Payment> rowMap(ResultSet resultSet) {
        Optional<Payment> optionalPayment = Optional.empty();
        try {
            Payment payment = Payment.newBuilder()
                    .setId(resultSet.getLong(AttributeName.PAYMENT_ID))
                    .setUserId(resultSet.getLong(AttributeName.USER_ID))
                    .setServiceId(resultSet.getLong(AttributeName.SERVICE_ID))
                    .setRemainedVisits(resultSet.getShort(AttributeName.REMAINED_VISITS))
                    .setPaid(resultSet.getBoolean(AttributeName.PAID))
                    .build();
            optionalPayment = Optional.of(payment);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
        }
        return optionalPayment;
    }
}
