package com.example.fitnessclub.model.entity;

public class Exercise extends AbstractEntity {

    private long appointmentId;
    private String name;
    private byte numberSets;
    private byte numberRepetitions;
    private String equipment;
    private String description;

    public static ExerciseBuilder newBuilder() {
        return new Exercise().new ExerciseBuilder();
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public class ExerciseBuilder {

        public ExerciseBuilder setId(Long id) {
            Exercise.this.setId(id);
            return this;
        }

        public ExerciseBuilder setAppointmentId(long appointmentId) {
            Exercise.this.appointmentId = appointmentId;
            return this;
        }

        public ExerciseBuilder setName(String exerciseName) {
            Exercise.this.name = exerciseName;
            return this;
        }

        public ExerciseBuilder setNumberSets(byte numberSets) {
            Exercise.this.numberSets = numberSets;
            return this;
        }

        public ExerciseBuilder setNumberRepetitions(byte numberRepetitions) {
            Exercise.this.numberRepetitions = numberRepetitions;
            return this;
        }

        public ExerciseBuilder setEquipment(String equipment) {
            Exercise.this.equipment = equipment;
            return this;
        }

        public ExerciseBuilder setDescription(String runTime) {
            Exercise.this.description = runTime;
            return this;
        }

        public Exercise build() {
            return Exercise.this;
        }
    }
}
