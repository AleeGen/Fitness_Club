package com.example.fitnessclub.model.dao;

import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.model.entity.Exercise;

import java.util.List;

public interface ExerciseDao {
    List<Exercise> findAll(long appointmentId) throws DaoException;
}
