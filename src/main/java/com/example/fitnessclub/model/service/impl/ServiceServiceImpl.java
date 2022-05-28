package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.dao.impl.ServiceDaoImpl;
import com.example.fitnessclub.model.entity.Service;
import com.example.fitnessclub.model.service.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ServiceServiceImpl implements ItemService {

    private static ServiceServiceImpl instance = new ServiceServiceImpl();

    private ServiceServiceImpl() {
    }

    public static ServiceServiceImpl getInstance() {
        return instance;
    }

    public List<Service> findAll() throws ServiceException {
        List<Service> services = new ArrayList<>();
        try {
            services = new ServiceDaoImpl().findAll();
        } catch (DaoException e) {
            //log
            throw new ServiceException(e);
        }
        return services;
    }
}
