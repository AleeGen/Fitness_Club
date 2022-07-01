package com.example.fitnessclub.model.dao;

public final class DatabaseQuery {

    private DatabaseQuery() {
    }

    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT user_id FROM fitness_club.users WHERE login = ?";

    public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT role, is_blocked FROM fitness_club.users WHERE login " +
            "= ? and password = ?";

    public static final String SELECT_USER_ALL_BY_LOGIN = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar, about_me, is_blocked FROM fitness_club.users WHERE login = ?";

    public static final String INSERT_USER = "INSERT INTO fitness_club.users (login, password, mail, name, " +
            "lastname, date_birth, sex, phone,number_card) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ALL_SERVICES = "SELECT service_id, service_name, number_visits, " +
            "validity_period, price, description FROM fitness_club.services";

    public static final String SELECT_SERVICE_BY_ID = "SELECT service_id, service_name, number_visits, " +
            "validity_period, price, description FROM fitness_club.services WHERE service_id = ?";

    public static final String INSERT_PAYMENT = "INSERT INTO fitness_club.payment (user_id, " +
            "service_id, remained_visits, paid) VALUES (?,?,?,?)";

    public static final String UPDATE_USER_AVATAR = "UPDATE fitness_club.users SET path_avatar = ? WHERE login = ?";

    public static final String UPDATE_USER = "UPDATE fitness_club.users SET mail = ?, name = ?, lastname = ?, " +
            "date_birth = ?, sex = ?, phone = ?, number_card = ?, about_me = ? WHERE login = ?";

    public static final String SELECT_PASSWORD = "SELECT password FROM fitness_club.users WHERE login = ?";

    public static final String UPDATE_PASSWORD = "UPDATE fitness_club.users SET password = ? WHERE login = ?";

    public static final String SELECT_APPOINTMENTS_BY_USER_ID = "SELECT appointment_id, date, type_appointment, " +
            "nutrition, user_id FROM fitness_club.appointment WHERE user_id = ?";

    public static final String SELECT_EXERCISE_BY_APP_ID = "SELECT exercise_id, name, number_sets, number_repetitions" +
            ", equipment, description, appointment_id FROM fitness_club.exercise WHERE appointment_id = ?";

    public static final String SELECT_ALL_USERS = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar, about_me, is_blocked FROM fitness_club.users";

    public static final String SELECT_ALL_TRAINERS = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar, about_me, is_blocked FROM fitness_club.users WHERE role = 'trainer'";

    public static final String UPDATE_FEATURES_USER = "UPDATE fitness_club.users SET role = ?," +
            "corporate = ?, discount_code = ? WHERE login = ?";

    public static final String UPDATE_PAYMENT = "UPDATE fitness_club.payment SET remained_visits = ?," +
            "expiry = ?, paid = ? WHERE payment_id = ?";

    public static final String SELECT_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid  FROM fitness_club.payment WHERE payment_id = ?";

    public static final String SELECT_ALL_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid  FROM fitness_club.payment WHERE user_id = ?";

    public static final String MONEY_TRANSFER = "Query";

    public static final String UPDATE_BUY_PAYMENT = "UPDATE fitness_club.payment SET paid = 1, " +
            "expiry = ? WHERE payment_id = ?";

    public static final String SELECT_APPOINTMENT = "SELECT appointment_id, date, type_appointment, " +
            "nutrition, user_id FROM fitness_club.appointment WHERE appointment_id = ?";

    public static final String SELECT_CONTRACT_BY_CLIENT_TRAINER = "SELECT contract_id, total_cost, start_date, " +
            "end_date, client_id, trainer_id FROM fitness_club.contract WHERE and trainer_id = ?";

    public static final String UPDATE_APPOINTMENT = "UPDATE fitness_club.appointment SET date = ?," +
            "type_appointment = ?, nutrition = ? WHERE appointment_id = ?";

    public static final String UPDATE_EXERCISE = "UPDATE fitness_club.exercise SET name = ?," +
            "number_sets = ?, number_repetitions = ?, equipment = ?, description = ? WHERE exercise_id = ?";

    public static final String SELECT_EXERCISE = "SELECT exercise_id, name, number_sets, number_repetitions" +
            ", equipment, description, appointment_id FROM fitness_club.exercise WHERE exercise_id = ?";

    public static final String DELETE_APPOINTMENT = "DELETE FROM fitness_club.appointment WHERE appointment_id = ?";

    public static final String INSERT_APPOINTMENT = "INSERT INTO fitness_club.appointment (date, type_appointment, " +
            "user_id) VALUES (?,?,?)";

    public static final String SELECT_ID_USER_BY_ID_APPOINTMENT = "SELECT user_id FROM fitness_club.appointment" +
            " WHERE appointment_id = ?";

    public static final String INSERT_EXERCISE = "INSERT INTO fitness_club.exercise (name, number_sets, " +
            "number_repetitions, equipment, description, appointment_id) VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_USER_BLOCKED = "UPDATE fitness_club.users SET is_blocked = ? WHERE login = ?";
}
