package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.model.entity.AbstractEntity;
import com.example.fitnessclub.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    protected Connection connection;

    public abstract boolean add(T t) throws DaoException;

    public abstract boolean delete(T t) throws DaoException;

    public abstract List<T> findAll() throws DaoException;

    public abstract T update(T t) throws DaoException;

    //// TODO: 24.04.2022 вроде как любой DAO должен иметь методы close по закрытию statement and connection, наверное надо дописать default методы
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //log
        }
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
