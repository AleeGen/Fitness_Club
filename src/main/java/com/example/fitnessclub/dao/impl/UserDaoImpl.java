package com.example.fitnessclub.dao.impl;

import com.example.fitnessclub.dao.BaseDao;
import com.example.fitnessclub.dao.UserDao;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl extends BaseDao<User> implements UserDao {


    private static final String SELECT_PASSWORD = "SELECT password FROM users WHERE lastname = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete unsupported"); //// TODO: 19.04.2022 если не собираюсь реализовывать, то исключение 
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD)) { 
            //// TODO: 24.04.2022 пересмотреть в learne про PrepareStatement, а именно про то как отключают коммиты, добавляются запросы, фиксируется это всё и обновляется
            statement.setString(1, login);
            //// TODO: 24.04.2022 Batch лучше использовать для повышения производительности
            ResultSet resultSet = statement.executeQuery();
            String pathFromDb;
            if (resultSet.next()) {
                pathFromDb = resultSet.getString(1); //// TODO: 18.04.2022 либо getString("password"), здесь начинается с 1 а не с 0
                match = password.equals(pathFromDb);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }
}
