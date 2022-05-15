package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.DatabaseQuery;
import com.example.fitnessclub.model.dao.ItemDao;
import com.example.fitnessclub.model.entity.Item;
import com.example.fitnessclub.model.mapper.impl.ItemMapper;
import com.example.fitnessclub.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl extends BaseDao<Item> implements ItemDao {
    @Override
    public boolean add(Item item) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Item item) throws DaoException {
        return false;
    }

    @Override
    public List<Item> findAll() throws DaoException {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SELECT_ALL_SERVICES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Optional<Item> item = ItemMapper.getInstance().rowMap(resultSet);
                item.ifPresent(items::add);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return items;
    }

    @Override
    public Item update(Item item) throws DaoException {
        return null;
    }

}
