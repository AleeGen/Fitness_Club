package com.example.fitnessclub.model.service.impl;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.dao.ItemDao;
import com.example.fitnessclub.model.dao.impl.ItemDaoImpl;
import com.example.fitnessclub.model.entity.Item;
import com.example.fitnessclub.model.service.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static ItemServiceImpl instance = new ItemServiceImpl();

    private ItemServiceImpl() {
    }

    public static ItemServiceImpl getInstance() {
        return instance;
    }

    public List<Item> findAll() throws ServiceException {
        List<Item> services = new ArrayList<>();
        try {
            services = new ItemDaoImpl().findAll();
        } catch (DaoException e) {
            //log
            throw new ServiceException(e);
        }
        return services;
    }
}
