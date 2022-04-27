package com.example.fitnessclub.dao.logic;

import com.example.fitnessclub.dao.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {   //// TODO: 24.04.2022 как я понял, используется для одной большой транзакции, то есть за один connection сделать действие над разными Dao
    private Connection connection;

    public void begin(BaseDao dao, BaseDao... daos) {
        if (connection == null) {
            //connection = take connection
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.setConnection(connection);
        for (BaseDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void end() {
        //check of null connection
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            //log
        }
        //release connection or closed
        connection = null;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            //log
        }
    }

    public void rollBack() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //log
        }
    }
}
