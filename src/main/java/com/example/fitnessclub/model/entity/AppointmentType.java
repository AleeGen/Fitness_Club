package com.example.fitnessclub.model.entity;

public enum AppointmentType {
    EXERCISE_ROOM("exercise_room"),
    FITNESS_ROOM("fitness_room"),
    DIET("diet"),
    SWIMMING("swimming"),
    YOGA("yoga"),
    MARTIAL_ARTS("martial_arts"),
    DEFAULT("default");

    private final String type;

    AppointmentType(String type) {
        this.type = type;
    }

    public static AppointmentType getType(String type) {
        for (AppointmentType t : AppointmentType.values()) {
            if (type.equals(t.type)) {
                return t;
            }
        }
        return DEFAULT;
    }
}
