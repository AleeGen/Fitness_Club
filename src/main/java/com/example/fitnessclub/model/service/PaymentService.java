package com.example.fitnessclub.model.service;

import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    boolean addToCart(String login, Long serviceId) throws ServiceException;

    List<Payment> findAll(String login, boolean status) throws ServiceException;

    Optional<Short> calculateCost(String login, long serviceId) throws ServiceException;

    Optional<Payment> find(String id) throws ServiceException;

    Optional<LocalDate> calculateExpiry(long serviceId) throws ServiceException;

    boolean buy(String login, long id, LocalDate expiry, short cost) throws ServiceException;
}
