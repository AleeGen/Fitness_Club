package com.example.fitnessclub.model.mapper.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.entity.UserRole;
import com.example.fitnessclub.model.mapper.RowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements RowMapper<User> {
    private static final Logger logger = LogManager.getLogger();

    private final String MAPPING_ERROR = "Mapping error in UserMapper class!";
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
                    .setId(resultSet.getLong(AttributeName.USER_ID)) //// FIXME: 15.05.2022 наверное, безопаснее убрать setId, а покупки устанавливать по Логину, он тоже уникален
                    .setLogin(resultSet.getString(AttributeName.LOGIN))
                    .setRole(UserRole.getRole(resultSet.getString(AttributeName.ROLE)))
                    .setMail(resultSet.getString(AttributeName.MAIL))
                    .setName(resultSet.getString(AttributeName.NAME))
                    .setLastname(resultSet.getString(AttributeName.LASTNAME))
                    .setDate_birth(resultSet.getDate(AttributeName.DATE_BIRTH))
                    .setSex(resultSet.getString(AttributeName.SEX))
                    .setPhone(resultSet.getString(AttributeName.PHONE))
                    .setCorporate(resultSet.getBoolean(AttributeName.CORPORATE))
                    .setVisitPeriodMonths(resultSet.getByte(AttributeName.VISIT_PERIOD_MONTHS))
                    .setDiscount_code(resultSet.getString(AttributeName.DISCOUNT_CODE))
                    .setNumberCard(resultSet.getString(AttributeName.NUMBER_CARD))
                    .build();
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
