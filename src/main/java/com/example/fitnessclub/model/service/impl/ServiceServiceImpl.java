package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.dao.impl.ServiceDaoImpl;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.service.ServiceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LogManager.getLogger();
    private static final ServiceServiceImpl instance = new ServiceServiceImpl();

    private ServiceServiceImpl() {
    }

    public static ServiceServiceImpl getInstance() {
        return instance;
    }

    public List<Service> findAll() throws ServiceException {
        List<Service> services;
        try {
            services = ServiceDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "An error occurred when finding services");
            throw new ServiceException(e);
        }
        return services;
    }
}
