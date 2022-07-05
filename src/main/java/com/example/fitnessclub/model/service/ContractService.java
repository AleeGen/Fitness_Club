package com.example.fitnessclub.model.service;

import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface ContractService {

    Optional<Short> calculate(String startDate, String endDate, String login) throws ServiceException;

    boolean add(long clientId, long trainerId, String start, String end, short totalCost) throws ServiceException;

    List<User> findClients(String login) throws ServiceException;
}
