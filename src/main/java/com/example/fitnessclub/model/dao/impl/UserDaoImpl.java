package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.UserDao;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.mapper.impl.UserMapper;
import com.example.fitnessclub.model.pool.ConnectionPool;

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
        System.out.println("+checkingExistence+");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_USER_BY_LOGIN)) {
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
        System.out.println("+addUser+");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_USER)) {
            System.out.println(user.getLogin());
            statement.setString(1, user.getLogin());
            System.out.println(user.getPassword());
            statement.setString(2, user.getPassword());
            System.out.println(user.getMail());
            statement.setString(3, user.getMail());
            System.out.println(user.getName());
            statement.setString(4, user.getName());
            System.out.println(user.getLastname());
            statement.setString(5, user.getLastname());
            System.out.println(user.getDateBirth());
            statement.setDate(6, user.getDateBirth());
            System.out.println(user.getSex());
            statement.setString(7, user.getSex());
            System.out.println(user.getPhone());
            statement.setString(8, user.getPhone());
            System.out.println(user.getNumberCard());
            if (user.getNumberCard().equals("")) {// FIXME: 12.05.2022  В БД номер карты установлен как decimal(20,0), поэтому он не принимает пустые "" строки, нужно передавать null чтобы избежать эксепшен
                statement.setString(9, null);
            } else {
                statement.setString(9, user.getNumberCard());
            }
            System.out.println("+addUser9+");
            int execute = statement.executeUpdate();
            System.out.println("+addUser10+");
            if (execute == 1) {
                System.out.println("+addUser11+");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException(e);
        }
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
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_USER_BY_LOGIN_PASSWORD)) {
            //// TODO: 24.04.2022 пересмотреть в learne про PrepareStatement, а именно про то как отключают коммиты, добавляются запросы, фиксируется это всё и обновляется
            System.out.println("4");
            statement.setString(1, login);
            statement.setString(2, codePassword);
            //// TODO: 24.04.2022 Batch лучше использовать для повышения производительности
            System.out.println("5");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("6");
            if (resultSet.next()) {
                System.out.println("7");
                optionalUser = UserMapper.getInstance().rowMap(resultSet);
                System.out.println("10");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DaoException(e.getMessage(),e);
        }
        return optionalUser;
    }


}
