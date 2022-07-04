package com.example.fitnessclub.model.dao;

public final class DatabaseQuery {

    private DatabaseQuery() {
    }

    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT user_id FROM fitness_club.users WHERE login = ?";

    public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT role, is_blocked FROM fitness_club.users WHERE login " +
            "= ? and password = ?";

    public static final String SELECT_USER_BY_ID = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount, " +
            "number_card, path_avatar, about_me, is_blocked, cash FROM fitness_club.users WHERE user_id = ?";

    public static final String SELECT_USER_BY_LOGIN = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount, " +
            "number_card, path_avatar, about_me, is_blocked, cash FROM fitness_club.users WHERE login = ?";

    public static final String INSERT_USER = "INSERT INTO fitness_club.users (login, password, mail, name, " +
            "lastname, date_birth, sex, phone, number_card) VALUES (?,?,?,?,?,?,?,?,?)";

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
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount, " +
            "number_card, path_avatar, about_me, is_blocked, cash FROM fitness_club.users";

    public static final String SELECT_ALL_TRAINERS = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount, " +
            "number_card, path_avatar, about_me, is_blocked, cash FROM fitness_club.users WHERE role = 'trainer'";

    public static final String UPDATE_FEATURES_USER = "UPDATE fitness_club.users SET role = ?," +
            "corporate = ?, discount = ? WHERE login = ?";

    public static final String UPDATE_PAYMENT = "UPDATE fitness_club.payment SET remained_visits = ?," +
            "expiry = ?, paid = ? WHERE payment_id = ?";

    public static final String SELECT_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid FROM fitness_club.payment WHERE payment_id = ?";

    public static final String SELECT_ALL_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid  FROM fitness_club.payment WHERE user_id = ?";

    public static final String UPDATE_BUY_PAYMENT = "UPDATE fitness_club.payment SET paid = 1, " +
            "expiry = ? WHERE payment_id = ?";

    public static final String SELECT_APPOINTMENT = "SELECT appointment_id, date, type_appointment, " +
            "nutrition, user_id FROM fitness_club.appointment WHERE appointment_id = ?";

    public static final String UPDATE_APPOINTMENT = "UPDATE fitness_club.appointment SET date = ?," +
            "type_appointment = ?, nutrition = ? WHERE appointment_id = ?";

    public static final String UPDATE_EXERCISE = "UPDATE fitness_club.exercise SET name = ?," +
            "number_sets = ?, number_repetitions = ?, equipment = ?, description = ? WHERE exercise_id = ?";

    public static final String SELECT_EXERCISE = "SELECT exercise_id, name, number_sets, number_repetitions" +
            ", equipment, description, appointment_id FROM fitness_club.exercise WHERE exercise_id = ?";

    public static final String DELETE_APPOINTMENT = "DELETE FROM fitness_club.appointment WHERE appointment_id = ?";

    public static final String INSERT_APPOINTMENT = "INSERT INTO fitness_club.appointment (date, type_appointment, " +
            "user_id) VALUES (?,?,?)";

    public static final String INSERT_EXERCISE = "INSERT INTO fitness_club.exercise (name, number_sets, " +
            "number_repetitions, equipment, description, appointment_id) VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_USER_BLOCKED = "UPDATE fitness_club.users SET is_blocked = ? WHERE login = ?";

    public static final String SELECT_SERVICE_PRICE_BY_ID = "SELECT price FROM fitness_club.services " +
            "WHERE service_id = ?";

    public static final String SELECT_DISCOUNT_BY_LOGIN = "SELECT discount FROM fitness_club.users WHERE login = ?";

    public static final String INSERT_CONTRACT = "INSERT INTO fitness_club.contract (client_id, trainer_id, " +
            "total_cost, start_date, end_date) VALUES (?,?,?,?,?)";

    public static final String MINUS_CASH = "UPDATE fitness_club.users SET cash = cash - ? WHERE user_id = ?";

    public static final String INSERT_PAYMENT_PAID = "INSERT INTO fitness_club.payment (user_id, " +
            "service_id, remained_visits, expiry, paid) VALUES (?,?,?,?,?)";

    public static final String PLUS_CASH = "UPDATE fitness_club.users SET cash = cash + ? WHERE login = ?";

    public static final String SELECT_CONTRACT_BY_CLIENT = "SELECT contract_id, trainer_id, client_id, start_date, " +
            "end_date, total_cost FROM fitness_club.contract WHERE end_date > NOW() and start_date < NOW() " +
            "and client_id = ?";

    public static final String SELECT_CONTRACTS_BY_TRAINER = "SELECT contract_id, trainer_id, client_id, start_date, " +
            "end_date, total_cost FROM fitness_club.contract WHERE end_date > NOW() and start_date < NOW() " +
            "and trainer_id = ?";
}
