package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceDao {

    List<Service> findAll() throws DaoException;

    Optional<Byte> takePrice(long id) throws DaoException;
}
