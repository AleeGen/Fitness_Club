package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.dao.impl.ServiceDaoImpl;
import com.example.fitnessclub.model.dao.impl.PaymentDaoImpl;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.PaymentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LogManager.getLogger();
    private static PaymentServiceImpl instance = new PaymentServiceImpl();

    private PaymentServiceImpl() {
    }

    public static PaymentServiceImpl getInstance() {
        return instance;
    }

    public boolean addToCart(String login, Long serviceId) {
        try {
            Optional<Service> item = ServiceDaoImpl.getInstance().find(serviceId.toString());
            Optional<User> user  = UserDaoImpl.getInstance().find(login);
            if (item.isPresent()) {
                byte remainedVisits = item.get().getNumberVisit();
                Payment payment = Payment.newBuilder()
                        .setUserId(user.get().getId())
                        .setServiceId(serviceId)
                        .setRemainedVisits(remainedVisits)
                        .build();
                PaymentDaoImpl paymentDao = PaymentDaoImpl.getInstance();
                if (paymentDao.add(payment)) {
                    return true;
                }
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "An error occurred while adding to the cart", e);
        }
        return false;
    }
}
