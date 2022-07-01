package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LogManager.getLogger();
    private static final PaymentServiceImpl instance = new PaymentServiceImpl();

    private PaymentServiceImpl() {
    }

    public static PaymentServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addToCart(String login, Long serviceId) throws ServiceException {
        try {
            Optional<Service> service = ServiceDaoImpl.getInstance().find(serviceId.toString());
            Optional<User> user = UserDaoImpl.getInstance().find(login);
            if (service.isPresent() && user.isPresent()) {
                byte remainedVisits = service.get().getNumberVisit();
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
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<Payment> findAll(String login, boolean status) throws ServiceException {
        List<Payment> listPayment = new ArrayList<>();
        try {
            Optional<User> optionalUser = UserDaoImpl.getInstance().find(login);
            if (optionalUser.isPresent()) {
                long userId = optionalUser.get().getId();
                listPayment = PaymentDaoImpl.getInstance().findAll(userId);
                for (int i = 0; i < listPayment.size(); i++) {
                    if (listPayment.get(i).isPaid() != status) {
                        listPayment.remove(i);
                        i--;
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return listPayment;
    }
}
