package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.UserDao;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.mapper.impl.UserMapper;
import com.example.fitnessclub.model.entity.UserRole;
import com.example.fitnessclub.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean checkingExistence(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_USER_ID_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean add(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getLastname());
            statement.setDate(6, user.getDateBirth());
            statement.setString(7, user.getSex());
            statement.setString(8, user.getPhone());
            if (user.getNumberCard().isBlank()) {
                statement.setString(9, null);
            } else {
                statement.setString(9, user.getNumberCard());
            }
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error during user registration");
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(String userId) {
        throw new UnsupportedOperationException("delete unsupported");
    }

    @Override
    public Optional<User> find(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_USER_ALL_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = UserMapper.getInstance().rowMap(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<User> user = UserMapper.getInstance().rowMap(resultSet);
                if (user.isPresent()) {
                    boolean isBlocked = resultSet.getBoolean(AttributeName.IS_BLOCKED);
                    user.get().setBlocked(isBlocked);
                    users.add(user.get());
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findTrainers() throws DaoException {
        List<User> trainers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_TRAINERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<User> trainer = UserMapper.getInstance().rowMap(resultSet);
                trainer.ifPresent(trainers::add);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainers;
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        Optional<User> result = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementUpdate = connection.prepareStatement(DatabaseQuery.UPDATE_USER)) {
            statementUpdate.setString(1, user.getMail());
            statementUpdate.setString(2, user.getName());
            statementUpdate.setString(3, user.getLastname());
            statementUpdate.setDate(4, user.getDateBirth());
            statementUpdate.setString(5, user.getSex());
            statementUpdate.setString(6, user.getPhone());
            statementUpdate.setString(7, user.getAboutMe());
            if (user.getNumberCard().isBlank()) {
                statementUpdate.setString(7, null);
            } else {
                statementUpdate.setString(7, user.getNumberCard());
            }
            statementUpdate.setString(8, user.getAboutMe());
            statementUpdate.setString(9, user.getLogin());
            if (statementUpdate.executeUpdate() == 1) {
                try (PreparedStatement statementFind = connection.prepareStatement(DatabaseQuery.SELECT_USER_ALL_BY_LOGIN)) {
                    statementFind.setString(1, user.getLogin());
                    try (ResultSet resultSet = statementFind.executeQuery()) {
                        if (resultSet.next()) {
                            result = UserMapper.getInstance().rowMap(resultSet);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<User> editFeatures(RequestParameters paramUser) throws DaoException {
        Optional<User> result = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statementFeatures = connection.prepareStatement(DatabaseQuery.UPDATE_FEATURES_USER)) {
            statementFeatures.setString(1, paramUser.get(AttributeName.ROLE));
            statementFeatures.setInt(2, Integer.parseInt(paramUser.get(AttributeName.CORPORATE)));
            statementFeatures.setString(3, paramUser.get(AttributeName.DISCOUNT_CODE));
            statementFeatures.setString(4, paramUser.get(AttributeName.LOGIN));
            if (statementFeatures.executeUpdate() == 1) {
                try (PreparedStatement statementFind = connection.prepareStatement(DatabaseQuery.SELECT_USER_ALL_BY_LOGIN)) {
                    statementFind.setString(1, paramUser.get(AttributeName.LOGIN));
                    try (ResultSet resultSet = statementFind.executeQuery()) {
                        if (resultSet.next()) {
                            result = UserMapper.getInstance().rowMap(resultSet);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean editAvatar(String pathAvatar, String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.UPDATE_USER_AVATAR)) {
            statement.setString(1, pathAvatar);
            statement.setString(2, login);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "An error occurred when edit avatar");
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<String> findPassword(String login) throws DaoException {
        Optional<String> password = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_PASSWORD)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = Optional.of(resultSet.getString(AttributeName.PASSWORD));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to get password");
            throw new DaoException(e);
        }
        return password;
    }

    @Override
    public boolean editPassword(String login, String replacePass) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.UPDATE_PASSWORD)) {
            statement.setString(1, replacePass);
            statement.setString(2, login);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to change password");
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> authenticate(String login, String codePassword) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_USER_BY_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, codePassword);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = User.newBuilder().setLogin(login)
                            .setRole(UserRole.getRole(resultSet.getString(AttributeName.ROLE)))
                            .setBlocked(resultSet.getBoolean(AttributeName.IS_BLOCKED))
                            .build();
                    optionalUser = Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean blocked(String login, boolean isBlocked) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.UPDATE_USER_BLOCKED)) {
            statement.setBoolean(1, isBlocked);
            statement.setString(2, login);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
