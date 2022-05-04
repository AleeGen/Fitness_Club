package com.example.fitnessclub.mapper;

import com.example.fitnessclub.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface RowMapper<T extends AbstractEntity> {
    Optional<T> rowMap(ResultSet resultSet);
}
