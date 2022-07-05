package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.dao.impl.ContractDaoImpl;
import com.example.fitnessclub.model.dao.impl.ServiceDaoImpl;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
import com.example.fitnessclub.model.entity.ContractCT;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.model.service.ContractService;
import com.example.fitnessclub.validation.ValidationUser;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContractServiceImpl implements ContractService {

    private static final ContractServiceImpl instance = new ContractServiceImpl();
    private static final long SERVICE_ID = 10;
    private static final short DAYS_PER_YEAR = 365;
    private static final byte DAYS_PER_MONTH = 30;
    private static final byte COEFFICIENT = 100;
    private static final short DISCOUNT_AFTER_DAYS = 365;
    private static final byte DISCOUNT_CORPORATE = 20;
    private static final byte DISCOUNT_REGULAR_CLIENTS = 10;

    private ContractServiceImpl() {
    }

    public static ContractServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Short> calculate(String startDate, String endDate, String login) throws ServiceException {
        Optional<Short> cost = Optional.empty();
        ValidationUser validation = ValidationUser.getInstance();
        if (validation.isValidDate(startDate) && validation.isValidDate(endDate)) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            LocalDate now = LocalDate.now();
            if (start.isBefore(end) && !start.isBefore(now)) {
                Period diff = Period.between(start, end);
                byte days = (byte) diff.getDays();
                byte months = (byte) diff.getMonths();
                byte years = (byte) diff.getYears();
                short countDays = (short) (years * DAYS_PER_YEAR + months * DAYS_PER_MONTH + days);
                try {
                    Optional<Byte> price = ServiceDaoImpl.getInstance().takePrice(SERVICE_ID);
                    Optional<User> user = UserDaoImpl.getInstance().find(login);
                    if (user.isPresent() && price.isPresent()) {
                        short visitPeriodDays = user.get().getVisitPeriodDays();
                        byte discount = 0;
                        if (user.get().isCorporate()) {
                            discount += DISCOUNT_CORPORATE;
                        }
                        if (visitPeriodDays > DISCOUNT_AFTER_DAYS) {
                            discount += DISCOUNT_REGULAR_CLIENTS;
                        }
                        short sum = (short) (price.get() * countDays);
                        short result = (short) (sum - sum * discount / COEFFICIENT);
                        cost = Optional.of(result);
                    }
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            }
        }
        return cost;
    }

    @Override
    public boolean add(long clientId, long trainerId, String start, String end, short totalCost) throws ServiceException {
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        ContractCT contract = ContractCT.newBuilder()
                .setUserId(clientId)
                .setTrainerId(trainerId)
                .setStart(startDate)
                .setEnd(endDate)
                .setTotalCost(totalCost)
                .build();
        try {
            return ContractDaoImpl.getInstance().add(contract);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findClients(String loginTrainer) throws ServiceException {
        List<User> users = new ArrayList<>();
        try {
            Optional<User> trainer = UserDaoImpl.getInstance().find(loginTrainer);
            if (trainer.isPresent()) {
                List<ContractCT> listContract = ContractDaoImpl.getInstance().findAll(trainer.get().getId());
                for (var contract : listContract) {
                    Optional<User> client = UserDaoImpl.getInstance().find(contract.getUserId());
                    client.ifPresent(users::add);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
