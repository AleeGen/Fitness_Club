package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.ContractCT;

import java.util.List;

public interface ContractDao {
    List<ContractCT> findAll(long trainerId) throws DaoException;
}
