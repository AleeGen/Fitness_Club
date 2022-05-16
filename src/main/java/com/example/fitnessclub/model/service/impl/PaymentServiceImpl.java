package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.impl.ItemDaoImpl;
import com.example.fitnessclub.model.dao.impl.PaymentDaoImpl;
import com.example.fitnessclub.model.entity.Item;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.service.PaymentService;

import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    private static PaymentServiceImpl instance = new PaymentServiceImpl();

    private PaymentServiceImpl() {
    }

    public static PaymentServiceImpl getInstance() {
        return instance;
    }

    public boolean addToCart(Long userId, Long serviceId) {
        try {
            ItemDaoImpl itemDao = new ItemDaoImpl();
            Optional<Item> item = itemDao.find(serviceId.toString());
            if (item.isPresent()) {
                byte remainedVisits = Byte.parseByte(item.get().getNumberVisit());
                Payment payment = Payment.newBuilder()
                        .setUserId(userId)
                        .setServiceId(serviceId)
                        .setRemainedVisits(remainedVisits)
                        .build();
                PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                if (paymentDao.add(payment)) {
                    return true;
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
            //log
        }
        return false;
    }
}
