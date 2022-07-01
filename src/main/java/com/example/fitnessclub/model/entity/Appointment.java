package com.example.fitnessclub.model.entity;

import java.sql.Date;

public class Appointment extends AbstractEntity {

    private long userId;
    private Date date;
    private String nutrition;
    private AppointmentType type;

    public static AppBuilder newBuilder() {
        return new Appointment().new AppBuilder();
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

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public class AppBuilder {

        public AppBuilder setId(Long id) {
            Appointment.this.setId(id);
            return this;
        }

        public AppBuilder setUserId(long userId) {
            Appointment.this.userId = userId;
            return this;
        }

        public AppBuilder setDate(Date date) {
            Appointment.this.date = date;
            return this;
        }

        public AppBuilder setNutrition(String nutrition) {
            Appointment.this.nutrition = nutrition;
            return this;
        }

        public AppBuilder setType(AppointmentType type) {
            Appointment.this.type = type;
            return this;
        }

        public Appointment build() {
            return Appointment.this;
        }
    }
}
