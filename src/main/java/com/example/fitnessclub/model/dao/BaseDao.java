package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.model.entity.AbstractEntity;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.User;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {

    public abstract boolean add(T t) throws DaoException;

    public abstract boolean delete(T t) throws DaoException;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<User> update(T t) throws DaoException;

}
