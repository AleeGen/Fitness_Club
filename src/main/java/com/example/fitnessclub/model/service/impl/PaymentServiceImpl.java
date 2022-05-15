package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.impl.ItemDaoImpl;
import com.example.fitnessclub.model.dao.impl.PaymentDaoImpl;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private static PaymentServiceImpl instance = new PaymentServiceImpl();

    private PaymentServiceImpl() {
    }

    public static PaymentServiceImpl getInstance() {
        return instance;
    }

    public boolean addToCart(Long userId, Long serviceId) {
        PaymentDaoImpl itemDao = new PaymentDaoImpl();
        //надо вызвать сначала Dao которое бы вернуло значение price и paid, чтобы занести их в конструктор ниже
        byte remainedVisits = //;
        boolean paid = //;
        Payment payment = new Payment(userId,serviceId,remainedVisits,paid);
        try {
            itemDao.add(payment);
        } catch (DaoException e) {
            e.printStackTrace();
            //log
        }
        return false;
    }
}
