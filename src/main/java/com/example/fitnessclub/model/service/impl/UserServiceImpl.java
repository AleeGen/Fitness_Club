package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.model.dao.impl.ItemDaoImpl;
import com.example.fitnessclub.model.mapper.ColumnName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.UserService;
import com.example.fitnessclub.validation.ValidationUser;
import java.sql.Date;
import java.util.Base64;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final String COLOR = "_color";
    private static final String COLOR_INVALID = "#FFEBE8";
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
            System.out.println("3");
            optionalUser = userDao.authenticate(login, codePassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean registration(RequestParameters paramUser) throws ServiceException {
        System.out.println("+registration+");
        ValidationUser validation = new ValidationUser();
        boolean exists = false;
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        if (paramUser.get(AttributeName.STEP_NUMBER).equals(AttributeName.STEP_ONE)) {
            System.out.println("if1");
            if (validation.isValidRegistrationStepOne(paramUser)) {
                System.out.println("if2");
                try {
                    if (!userDao.checkingExistence(paramUser.get(ColumnName.LOGIN))) {
                        System.out.println("if3");
                        exists = true;
                    } else {
                        paramUser.put(ColumnName.LOGIN, MessagePage.USER_EXISTS);
                        paramUser.put(ColumnName.LOGIN + COLOR, COLOR_INVALID);
                        System.out.println(ColumnName.LOGIN + COLOR);
                        paramUser.put(ColumnName.PASSWORD, "");
                    }
                } catch (DaoException e) {
                    //log
                }
            }
        } else if (paramUser.get(AttributeName.STEP_NUMBER).equals(AttributeName.STEP_TWO)) {
            System.out.println("if4");
            if (validation.isValidRegistrationStepTwo(paramUser)) {
                System.out.println("if5");
                try {
                    String password = paramUser.get(ColumnName.PASSWORD);
                    String codePassword = Base64.getEncoder().encodeToString(password.getBytes());
                    Date date = null;
                    try {
                        date = Date.valueOf(paramUser.get(ColumnName.DATE_BIRTH));
                    } catch (Exception e) {
                        //log
                    }
                    System.out.println("--->"+paramUser.get(ColumnName.NUMBER_CARD)+"<---");
                    User user = User.newBuilder()
                            .setLogin(paramUser.get(ColumnName.LOGIN))
                            .setPassword(codePassword)
                            .setMail(paramUser.get(ColumnName.MAIL))
                            .setName(paramUser.get(ColumnName.NAME))
                            .setLastname(paramUser.get(ColumnName.LASTNAME))
                            .setDate_birth(date)
                            .setSex(paramUser.get(ColumnName.SEX))
                            .setPhone(paramUser.get(ColumnName.PHONE))
                            .setNumberCard(paramUser.get(ColumnName.NUMBER_CARD))
                            .build();
                    System.out.println("if6");
                    exists = UserDaoImpl.getInstance().add(user);
                    System.out.println("if7");
                } catch (DaoException | IllegalArgumentException e) {
                    throw new ServiceException(e);
                }
            }
            System.out.println("if8");
        }
        return exists;
    }

}
