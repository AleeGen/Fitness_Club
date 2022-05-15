package com.example.fitnessclub.model.mapper;

import com.example.fitnessclub.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface RowMapper<T extends AbstractEntity> {
    Optional<T> rowMap(ResultSet resultSet);
}
