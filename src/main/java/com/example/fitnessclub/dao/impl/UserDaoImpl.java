package com.example.fitnessclub.dao.impl;

import com.example.fitnessclub.dao.BaseDao;
import com.example.fitnessclub.constants.DatabaseQueries;
import com.example.fitnessclub.dao.UserDao;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.mapper.impl.UserMapper;
import com.example.fitnessclub.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean checkingExistence(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQueries.SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean add(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQueries.INSERT_USER)) {
            System.out.println("->41");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getLastname());
            System.out.println("->42");
            statement.setDate(6, user.getDateBirth());
            statement.setString(7, user.getSex());
            statement.setString(8, user.getPhone());
            statement.setString(9, Integer.toString(user.getNumberCard()));
            int execute = statement.executeUpdate(); //// TODO: 03.05.2022  Statement.executeQuery() cannot issue statements that do not produce result sets.
            System.out.println("->43");
            System.out.println("->n " + execute);
            if (execute == 1) {
                System.out.println("->44");
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        System.out.println("->45");
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
    public Optional<User> authenticate(String login, String codePassword) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQueries.SELECT_USER_BY_LOGIN_PASSWORD)) {
            //// TODO: 24.04.2022 пересмотреть в learne про PrepareStatement, а именно про то как отключают коммиты, добавляются запросы, фиксируется это всё и обновляется
            statement.setString(1, login);
            statement.setString(2, codePassword);
            //// TODO: 24.04.2022 Batch лучше использовать для повышения производительности
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalUser = UserMapper.getInstance().rowMap(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }


}
