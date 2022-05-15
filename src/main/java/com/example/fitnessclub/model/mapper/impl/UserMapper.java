package com.example.fitnessclub.model.mapper.impl;

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
            System.out.println("8");
            User user = User.newBuilder()
                    .setId(resultSet.getLong(ColumnName.USERS_ID)) //// FIXME: 15.05.2022 наверное, безопаснее убрать setId, а покупки устанавливать по Логину, он тоже уникален
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
                    .setNumberCard(resultSet.getString(ColumnName.NUMBER_CARD))
                    .build();
            System.out.println("9");
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
