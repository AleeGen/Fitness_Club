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

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LogManager.getLogger();
    private static final PaymentServiceImpl instance = new PaymentServiceImpl();
    private static final byte COEFFICIENT = 100;

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

    @Override
    public Optional<Short> calculateCost(String login, long serviceId) throws ServiceException {
        Optional<Short> result = Optional.empty();
        try {
            Optional<User> user = UserDaoImpl.getInstance().find(login);
            if (user.isPresent()) {
                Optional<Byte> price = ServiceDaoImpl.getInstance().takePrice(serviceId);
                if (price.isPresent()) {
                    short cost = (short) (price.get() - price.get() * user.get().getDiscount() / COEFFICIENT);
                    result = Optional.of(cost);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<Payment> find(String id) throws ServiceException {
        try {
            return PaymentDaoImpl.getInstance().find(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<LocalDate> calculateExpiry(long serviceId) throws ServiceException {
        Optional<LocalDate> result = Optional.empty();
        try {
            Optional<Service> service = ServiceDaoImpl.getInstance().find(String.valueOf(serviceId));
            if (service.isPresent()) {
                byte validityPeriod = service.get().getValidityPeriod();
                LocalDate expiry = LocalDate.now().plusDays(validityPeriod);
                result = Optional.of(expiry);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean buy(String login, long paymentId, LocalDate expiry, short cost) throws ServiceException {
        Date date = Date.valueOf(expiry);
        boolean result = false;
        try {
            Optional<User> user = UserServiceImpl.getInstance().findByLogin(login);
            if (user.isPresent()) {
                long userId = user.get().getId();
                result = PaymentDaoImpl.getInstance().buy(userId, paymentId, date, cost);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
