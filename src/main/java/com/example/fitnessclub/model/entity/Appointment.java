package com.example.fitnessclub.model.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Appointment extends AbstractEntity {

    private long appointmentId;
    private long userId;
    private Date date;
    private AppointmentType type;
    private String exerciseName;
    private byte numberSets;
    private byte numberRepetitions;
    private String equipment;
    private String nutrition;
    private Time runTime;

    public static Appointment.AppointmentBuilder newBuilder() {
        return new Appointment().new AppointmentBuilder();
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public byte getNumberSets() {
        return numberSets;
    }

    public void setNumberSets(byte numberSets) {
        this.numberSets = numberSets;
    }

    public byte getNumberRepetitions() {
        return numberRepetitions;
    }

    public void setNumberRepetitions(byte numberRepetitions) {
        this.numberRepetitions = numberRepetitions;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public Time getRunTime() {
        return runTime;
    }

    public void setRunTime(Time runTime) {
        this.runTime = runTime;
    }

    public class AppointmentBuilder {

        public Appointment.AppointmentBuilder setId(Long Id) {
            Appointment.this.setId(Id);
            return this;
        }

        public Appointment.AppointmentBuilder setAppointmentId(long appointmentId) {
            Appointment.this.appointmentId = appointmentId;
            return this;
        }

        public Appointment.AppointmentBuilder setUserId(long userId) {
            Appointment.this.userId = userId;
            return this;
        }

        public Appointment.AppointmentBuilder setDate(Date date) {
            Appointment.this.date = date;
            return this;
        }

        public Appointment.AppointmentBuilder setType(AppointmentType type) {
            Appointment.this.type= type;
            return this;
        }

        public Appointment.AppointmentBuilder setExerciseName(String exerciseName) {
            Appointment.this.exerciseName= exerciseName;
            return this;
        }

        public Appointment.AppointmentBuilder setNumberSets(byte numberSets) {
            Appointment.this.numberSets= numberSets;
            return this;
        }

        public Appointment.AppointmentBuilder setNumberRepetitions(byte numberRepetitions) {
            Appointment.this.numberRepetitions= numberRepetitions;
            return this;
        }

        public Appointment.AppointmentBuilder setEquipment(String equipment) {
            Appointment.this.equipment= equipment;
            return this;
        }

        public Appointment.AppointmentBuilder setNutrition(String nutrition) {
            Appointment.this.nutrition= nutrition;
            return this;
        }

        public Appointment.AppointmentBuilder setRunTime(Time runTime) {
            Appointment.this.runTime= runTime;
            return this;
        }


        public Appointment build() {
            return Appointment.this;
        }
    }
}
