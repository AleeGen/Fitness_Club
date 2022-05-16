package com.example.fitnessclub.model.mapper.impl;

import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.entity.UserRole;
import com.example.fitnessclub.model.mapper.ColumnName;
import com.example.fitnessclub.model.mapper.RowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentMapper implements RowMapper<Payment> {

    private static final Logger logger = LogManager.getLogger();

    private final String MAPPING_ERROR = "Mapping error in PaymentMapper class!";
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
       /* Optional<Payment> optionalPayment;
        try {
            System.out.println("8p");
            Payment payment = new Payment(
              resultSet.get
            );
            System.out.println("9p");
            optionalPayment = Optional.of(Payment);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalPayment = Optional.empty();
        }*/
        return null; //optionaPayment;
    }
}
