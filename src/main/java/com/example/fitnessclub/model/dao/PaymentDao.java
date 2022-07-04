package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.Payment;

import java.sql.Date;
import java.util.List;

public interface PaymentDao {

    List<Payment> findAll(long userId) throws DaoException;

    boolean buy(long userId, long paymentId, Date exercise, short cost) throws DaoException;
}
