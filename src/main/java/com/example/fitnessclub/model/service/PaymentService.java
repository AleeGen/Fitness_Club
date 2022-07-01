package com.example.fitnessclub.model.service;

import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Payment;

import java.util.List;

public interface PaymentService {

    boolean addToCart(String login, Long serviceId) throws ServiceException;

    List<Payment> findAll(String login, boolean status) throws ServiceException;
}
