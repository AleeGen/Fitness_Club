package com.example.fitnessclub.model.mapper.impl;

import com.example.fitnessclub.model.entity.Item;
import com.example.fitnessclub.model.mapper.ColumnName;
import com.example.fitnessclub.model.mapper.RowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ItemMapper implements RowMapper<Item> {

    private static final Logger logger = LogManager.getLogger();

    private final String MAPPING_ERROR = "Mapping error in ItemMapper class!";
    private static ItemMapper instance;

    public static ItemMapper getInstance() {
        if (instance == null) {
            instance = new ItemMapper();
        }
        return instance;
    }

    private ItemMapper() {
    }

    @Override
    public Optional<Item> rowMap(ResultSet resultSet) {
        Optional<Item> optionalItem;
        try {
            System.out.println("id services");
            System.out.println(resultSet.getInt(ColumnName.SERVICE_ID));
            Item item = new Item(
                    resultSet.getLong(ColumnName.SERVICE_ID),
                    resultSet.getString(ColumnName.SERVICE_NAME),
                    resultSet.getString(ColumnName.NUMBER_VISITS),
                    resultSet.getInt(ColumnName.PRICE),
                    resultSet.getString(ColumnName.DESCRIPTION)
            );
            optionalItem = Optional.of(item);
        } catch (SQLException e) {
            logger.log(Level.ERROR, MAPPING_ERROR, e);
            optionalItem = Optional.empty();
        }
        return optionalItem;
    }
}
