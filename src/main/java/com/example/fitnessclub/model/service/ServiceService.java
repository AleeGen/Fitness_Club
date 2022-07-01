package com.example.fitnessclub.model.service;

import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.entity.Service;

import java.util.List;

public interface ServiceService {

    List<Service> findAll() throws ServiceException;
}
