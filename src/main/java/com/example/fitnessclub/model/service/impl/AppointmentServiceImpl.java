package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.model.dao.impl.AppointmentDaoImpl;
import com.example.fitnessclub.model.entity.Appointment;
import com.example.fitnessclub.model.service.AppointmentService;
import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
import com.example.fitnessclub.controller.RequestParameters;
import com.example.fitnessclub.model.dao.impl.UserDaoImpl;
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
import java.util.*;

public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LogManager.getLogger();
    private static AppointmentServiceImpl instance = new AppointmentServiceImpl();

    private AppointmentServiceImpl() {
    }

    public static AppointmentServiceImpl getInstance() {  //// TODO: 29.05.2022 сделать потокобезопасным?
        return instance;
    }

    public List<Appointment> findAll(long userId) throws ServiceException{
        AppointmentDaoImpl appointmentDao = AppointmentDaoImpl.getInstance();
        List<Appointment> appointments= new ArrayList<>();
        try {
            appointments = appointmentDao.findAll(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return appointments;
    }
}
