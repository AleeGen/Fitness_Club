/*
package com.example.fitnessclub;

import com.example.fitnessclub.entity.AbstractEntity;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

public class HelpBlinov2 { //// TODO: 23.04.2022 https://www.guru99.com/learn-sql-injection-with-practical-example.html#2
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "sqldata/database.properties";
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL = "/////////";
    private static String fileProperties;

    static {
        try {
            ClassLoader loader = ConnectionPool.class.getClassLoader();
            URL resource = loader.getResource(FILE_NAME);
            if (resource == null) {
                logger.log(Level.ERROR, "Resource is null! " + FILE_NAME);
                throw new IllegalArgumentException();
            }
            fileProperties = resource.getFile();
            properties.load(new FileReader(fileProperties));
            String driverName = (String) properties.get("db.driver");
        } catch (Exception e) {

        }
    }

    //------------------
    @FunctionalInterface
    public interface CustomRowMapper<T extends AbstractEntity> {   //// TODO: 24.04.2022 что-то достаем 
        */
/**
         * Map row optional.
         *
         * @param resultSet the result set
         * @return the optional
         * @throws DaoException the dao exception
         *//*

        Optional<T> mapRow(ResultSet resultSet) throws DaoException;
    }

    //------------------
    public Optional<User> mapRow(ResultSet resultSet) {  //// TODO: 24.04.2022 реализация одно из методов (доставать юзера)
        User user = new User();
        Optional<User> optionalUser;
        try {
            user.setUserId(resultSet.getLong(USER_ID));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPhoneNumber(resultSet.getInt(PHONE_NUMBER));
            user.setBirthday(resultSet.getDate(BIRTHDAY).toLocalDate());
            user.setDiscountId(resultSet.getLong(DISCOUNT_ID));
            user.setState(User.UserState.valueOf(resultSet.getString(USER_STATE)
                    .trim().toUpperCase()));
            user.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE)
                    .trim().toUpperCase()));
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
    //------------------
    try(
    InputStream inputStream = ConnectionPool.class.getClassLoader()
            .getResourceAsStream("database.properties"))

    {
        properties.load(inputStream);

    }*/
