package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.Appointment;

import java.util.List;

public interface AppointmentDao {
    List<Appointment> findAll(long userId) throws DaoException;
}
