package com.example.fitnessclub.mapper.impl;

import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.entity.UserRole;
import com.example.fitnessclub.constants.ColumnName;
import com.example.fitnessclub.mapper.RowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements RowMapper<User> {
    private static final Logger logger = LogManager.getLogger();

    private static final String TRUE = "1";

    private static UserMapper instance;

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    private UserMapper() {

    }

    @Override
    public Optional<User> rowMap(ResultSet resultSet) {
        Optional<User> optionalUser;
        try {
            User user = User.newBuilder()
                    .setRole(UserRole.getRole(resultSet.getString(ColumnName.ROLE)))
                    .setMail(resultSet.getString(ColumnName.MAIL))
                    .setName(resultSet.getString(ColumnName.NAME))
                    .setLastname(resultSet.getString(ColumnName.LASTNAME))
                    .setDate_birth(resultSet.getDate(ColumnName.DATE_BIRTH))
                    .setSex(resultSet.getString(ColumnName.SEX))
                    .setPhone(resultSet.getString(ColumnName.PHONE))
                    .setCorporate(resultSet.getBoolean(ColumnName.CORPORATE))
                    .setVisitPeriodMonths(resultSet.getByte(ColumnName.VISIT_PERIOD_MONTHS))
                    .setDiscount_code(resultSet.getString(ColumnName.DISCOUNT_CODE))
                    .setNumberCard(resultSet.getInt(ColumnName.NUMBER_CARD))
                    .build();
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Mapping error in UserMapper class!", e);
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
