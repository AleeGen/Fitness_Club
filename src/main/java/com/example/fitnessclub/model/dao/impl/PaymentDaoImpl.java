package com.example.fitnessclub.model.dao.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.BaseDao;
import com.example.fitnessclub.model.dao.PaymentDao;
import com.example.fitnessclub.model.entity.Payment;

import java.util.List;

public class PaymentDaoImpl extends BaseDao<Payment> implements PaymentDao {
    @Override
    public boolean add(Payment payment) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Payment payment) throws DaoException {
        return false;
    }

    @Override
    public List<Payment> findAll() throws DaoException {
        return null;
    }

    @Override
    public Payment update(Payment payment) throws DaoException {
        return null;
    }
}
