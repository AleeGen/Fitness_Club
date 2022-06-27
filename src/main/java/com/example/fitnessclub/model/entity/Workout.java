package com.example.fitnessclub.model.entity;

import java.util.List;

public class Workout {

    private Appointment appointment;
    private List<Exercise> exercises;

    public Workout(Appointment appointment, List<Exercise> exerciseList) {
        this.appointment = appointment;
        this.exercises = exerciseList;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
