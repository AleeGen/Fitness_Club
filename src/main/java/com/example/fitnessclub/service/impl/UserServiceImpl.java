package com.example.fitnessclub.service.impl;

import com.example.fitnessclub.constants.ColumnName;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.dao.impl.UserDaoImpl;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.service.UserService;

import java.sql.Date;
import java.util.Base64;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        String codePassword = Base64.getEncoder().encodeToString(password.getBytes());
        Optional<User> optionalUser = Optional.empty();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            optionalUser = userDao.authenticate(login, codePassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean registration(RequestParameters paramUser) throws ServiceException {
        System.out.println("->31");
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean exists = false;
        try {
            if (!userDao.checkingExistence(paramUser.get(ColumnName.LOGIN))) {
                System.out.println("->32");
                String password = paramUser.get(ColumnName.PASSWORD);
                String codePassword = Base64.getEncoder().encodeToString(password.getBytes());
                System.out.println("->33");
                Date date = null;
                try {
                    date = Date.valueOf(paramUser.get(ColumnName.DATE_BIRTH));
                } catch (Exception e) {

                }

                User user = User.newBuilder()
                        .setLogin(paramUser.get(ColumnName.LOGIN))
                        .setPassword(codePassword)
                        .setMail(paramUser.get(ColumnName.MAIL))
                        .setName(paramUser.get(ColumnName.NAME))
                        .setLastname(paramUser.get(ColumnName.LASTNAME))
                        .setDate_birth(date)
                        //.setSex(paramUser.get(ColumnName.SEX))
                        //.setPhone(paramUser.get(ColumnName.PHONE))
                        //.setNumberCard(Integer.parseInt(paramUser.get(ColumnName.NUMBER_CARD)))
                        .build();
                System.out.println("->34");
                exists = UserDaoImpl.getInstance().add(user);
                System.out.println("->35");
            }
        } catch (DaoException | IllegalArgumentException e) {
            throw new ServiceException(e);
        }
        System.out.println("->36");
        return exists;
    }
}
