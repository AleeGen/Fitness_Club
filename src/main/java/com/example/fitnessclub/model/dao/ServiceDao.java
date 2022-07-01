package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.Service;

import java.util.List;

public interface ServiceDao {
    List<Service> findAll() throws DaoException;
}
