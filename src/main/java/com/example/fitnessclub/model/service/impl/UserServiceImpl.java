package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.dao.impl.PaymentDaoImpl;
import com.example.fitnessclub.model.dao.impl.ServiceDaoImpl;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
import com.example.fitnessclub.model.entity.Payment;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.pool.ConnectionPool;
import com.example.fitnessclub.model.service.UserService;
import com.example.fitnessclub.model.service.mail.MailMain;
import com.example.fitnessclub.validation.TypeInvalid;
import com.example.fitnessclub.validation.ValidationUser;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY = "";
    private static final String COLOR = "_color";
    private static final String COLOR_INVALID = "#FFEBE8";
    private static final String PATH_PROPERTIES = "prop/uploadPath.properties";
    private static final String PATH_AVATAR = "path.avatar";
    private static final String ENCODE = "UTF-8";
    private static final String WRONG_PASSWORD = "Wrong password";
    private static final String TRUE = "1";
    private static final String FALSE = "0";
    private static final Properties property = new Properties();
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        String codePassword = Base64.getEncoder().encodeToString(password.getBytes());
        Optional<User> optionalUser;
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
        ValidationUser validation = ValidationUser.getInstance();
        boolean exists = false;
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        if (paramUser.get(AttributeName.STEP_NUMBER).equals(AttributeName.STEP_ONE)) {
            if (validation.isValidRegistrationStepOne(paramUser)) {
                try {
                    if (!userDao.checkingExistence(paramUser.get(AttributeName.LOGIN))) {
                        exists = true;
                    } else {
                        paramUser.put(AttributeName.LOGIN, MessagePage.USER_EXISTS);
                        paramUser.put(AttributeName.LOGIN + COLOR, COLOR_INVALID);
                        paramUser.put(AttributeName.PASSWORD, EMPTY);
                        paramUser.put(AttributeName.REPEAT_PASSWORD, EMPTY);
                    }
                } catch (DaoException e) {
                    logger.log(Level.ERROR, "Error checking the presence of a user");
                }
            }
        } else if (paramUser.get(AttributeName.STEP_NUMBER).equals(AttributeName.STEP_TWO)) {
            exists = registrationStepTwo(paramUser);
        }
        return exists;
    }

    private boolean registrationStepTwo(RequestParameters paramUser) throws ServiceException {
        boolean exists = false;
        if (ValidationUser.getInstance().isValidRegistrationStepTwo(paramUser)) {
            try {
                String password = paramUser.get(AttributeName.PASSWORD);
                String codePassword = Base64.getEncoder().encodeToString(password.getBytes());
                String date = paramUser.get(AttributeName.DATE_BIRTH);
                Date dateResult = date.isBlank() ? null : Date.valueOf(date);
                User user = User.newBuilder()
                        .setLogin(paramUser.get(AttributeName.LOGIN))
                        .setPassword(codePassword)
                        .setMail(paramUser.get(AttributeName.MAIL))
                        .setName(paramUser.get(AttributeName.NAME))
                        .setLastname(paramUser.get(AttributeName.LASTNAME))
                        .setDateBirth(dateResult)
                        .setSex(paramUser.get(AttributeName.SEX))
                        .setPhone(paramUser.get(AttributeName.PHONE))
                        .setNumberCard(paramUser.get(AttributeName.NUMBER_CARD))
                        .build();
                exists = UserDaoImpl.getInstance().add(user);
                MailMain.sendTo(paramUser);
            } catch (IOException e) {
                logger.log(Level.ERROR, "Failed to send notification to email");
            } catch (DaoException e) {
                logger.log(Level.ERROR, "Error when adding a user");
                throw new ServiceException(e);
            }
        }
        return exists;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = UserDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<User> findTrainers() throws ServiceException {
        List<User> trainers;
        try {
            trainers = UserDaoImpl.getInstance().findTrainers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }

    @Override
    public Optional<User> find(String login) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();
        if (login != null) {
            try {
                optionalUser = UserDaoImpl.getInstance().find(login);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return optionalUser;
    }

    @Override
    public Optional<User> update(RequestParameters paramUser) throws ServiceException {
        Optional<User> result;
        String date = paramUser.get(AttributeName.DATE_BIRTH);
        Date dateResult = date.isBlank() ? null : Date.valueOf(date);
        boolean isValid = ValidationUser.getInstance().isValidEditUser(paramUser);
        User user = User.newBuilder()
                .setLogin(paramUser.get(AttributeName.LOGIN))
                .setMail(paramUser.get(AttributeName.MAIL))
                .setName(paramUser.get(AttributeName.NAME))
                .setLastname(paramUser.get(AttributeName.LASTNAME))
                .setDateBirth(dateResult)
                .setSex(paramUser.get(AttributeName.SEX))
                .setPhone(paramUser.get(AttributeName.PHONE))
                .setNumberCard(paramUser.get(AttributeName.NUMBER_CARD))
                .setAboutMe(paramUser.get(AttributeName.ABOUT_ME))
                .build();
        if (isValid) {
            try {
                result = UserDaoImpl.getInstance().update(user);
                paramUser.put(MessagePage.MESSAGE, MessagePage.EDIT_USER_SUCCESSFULLY);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            result = Optional.of(user);
            paramUser.put(MessagePage.MESSAGE, MessagePage.EDIT_USER_FAILED);
        }
        return result;
    }

    @Override
    public Optional<User> editFeatures(RequestParameters paramUser) throws ServiceException {
        Optional<User> result = Optional.empty();
        boolean isValid = ValidationUser.getInstance().isValidEditFeatures(paramUser);
        if (isValid) {
            if (Boolean.parseBoolean(paramUser.get(AttributeName.CORPORATE))) {
                paramUser.put(AttributeName.CORPORATE, TRUE);
            } else {
                paramUser.put(AttributeName.CORPORATE, FALSE);
            }
            try {
                result = UserDaoImpl.getInstance().editFeatures(paramUser);
                paramUser.clear();
                paramUser.put(MessagePage.MESSAGE, MessagePage.EDIT_USER_SUCCESSFULLY);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            paramUser.put(MessagePage.MESSAGE, MessagePage.EDIT_USER_FAILED);
        }
        return result;
    }

    @Override
    public boolean editAvatar(Part part, String login) throws ServiceException {
        InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(PATH_PROPERTIES);
        try {
            property.load(inputStream);
        } catch (IOException e) {
            logger.fatal("Properties not load", e);
            throw new ServiceException(e);
        }
        String savePath = property.getProperty(PATH_AVATAR);
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String path = part.getSubmittedFileName();
        String randFileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
        try {
            String pathAvatar = savePath + File.separator + randFileName;
            part.write(pathAvatar);
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            return userDao.editAvatar(URLEncoder.encode(pathAvatar, ENCODE), login);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Failed to write to the server at the specified path");
            throw new ServiceException(e);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Failed to perform an update in the database");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean editPassword(RequestParameters parameters) throws ServiceException {
        String currentPass = parameters.get(AttributeName.PASSWORD);
        currentPass = Base64.getEncoder().encodeToString(currentPass.getBytes());
        String login = parameters.get(AttributeName.LOGIN);
        boolean result = false;
        if (ValidationUser.getInstance().isValidEditPassword(parameters)) {
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            try {
                Optional<String> password = userDao.findPassword(login);
                if (password.isPresent() && currentPass.equals(password.get())) {
                    parameters.put(AttributeName.PASSWORD, EMPTY);
                    String replacePass = parameters.get(AttributeName.REPLACE_PASSWORD);
                    replacePass = Base64.getEncoder().encodeToString(replacePass.getBytes());
                    if (userDao.editPassword(login, replacePass)) {
                        result = true;
                    }
                } else {
                    parameters.put(AttributeName.PASSWORD, WRONG_PASSWORD);
                    parameters.put(AttributeName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                parameters.put(AttributeName.REPLACE_PASSWORD, EMPTY);
                parameters.put(AttributeName.REPEAT_PASSWORD, EMPTY);
            }
        }
        return result;
    }

    @Override
    public boolean buy(String login, long paymentId) throws ServiceException {
        if (canBuy(login)) {
            try {
                Optional<Payment> optionalPayment = PaymentDaoImpl.getInstance().find(String.valueOf(paymentId));
                if (optionalPayment.isPresent()) {
                    String serviceId = String.valueOf(optionalPayment.get().getServiceId());
                    Optional<Service> optionalService = ServiceDaoImpl.getInstance().find(serviceId);
                    if (optionalService.isPresent()) {
                        long validityPeriod = optionalService.get().getValidityPeriod();
                        LocalDateTime now = LocalDateTime.now();
                        LocalDateTime exercise = now.plusDays(validityPeriod);
                        String result = exercise.format(DateTimeFormatter.ISO_LOCAL_DATE);
                        if (PaymentDaoImpl.getInstance().buy(paymentId, Date.valueOf(result))) {
                            return true;
                        }
                    }
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    @Override
    public boolean blocked(String login, String isBlocked) throws ServiceException {
        boolean blocked = Boolean.parseBoolean(isBlocked);
        try {
            return UserDaoImpl.getInstance().blocked(login, blocked);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean canBuy(String login) {
        return true;
    }
}
