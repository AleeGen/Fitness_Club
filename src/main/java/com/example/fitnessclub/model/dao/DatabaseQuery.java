package com.example.fitnessclub.model.dao;

public final class DatabaseQuery {

    private DatabaseQuery() {
    }

    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT user_id FROM fitness_club.users WHERE login = ?";

    public static final String SELECT_ROLE_BY_LOGIN_PASSWORD = "SELECT role FROM fitness_club.users WHERE login " +
            "= ? and password = ?";

    public static final String SELECT_USER_ALL_BY_LOGIN = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar, about_me FROM fitness_club.users WHERE login = ?";

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
            "date_birth = ?, sex = ?, phone = ?, number_card = ?, about_me =? WHERE login = ?";

    public static final String SELECT_PASSWORD = "SELECT password FROM fitness_club.users WHERE login = ?";

    public static final String UPDATE_PASSWORD = "UPDATE fitness_club.users SET password = ? WHERE login = ?";

    public static final String SELECT_APPOINTMENTS_BY_USER_ID = "SELECT appointment_id, date, type_appointment, " +
            "nutrition, user_id FROM fitness_club.appointment WHERE user_id = ?";

    public static final String SELECT_EXERCISE_BY_APP_ID = "SELECT exercise_id, name, number_sets, number_repetitions" +
            ", equipment, description, appointment_id FROM fitness_club.exercise WHERE appointment_id = ?";

    public static final String SELECT_ALL_USERS = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar, about_me FROM fitness_club.users";

    public static final String UPDATE_FEATURES_USER = "UPDATE fitness_club.users SET role = ?," +
            "corporate = ?, discount_code = ? WHERE login = ?";

    public static final String UPDATE_PAYMENT = "UPDATE fitness_club.payment SET remained_visits = ?," +
            "expiry = ?, paid = ? WHERE payment_id = ?";

    public static final String SELECT_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid  FROM fitness_club.payment WHERE payment_id = ?";

    public static final String SELECT_ALL_PAYMENT = "SELECT payment_id, user_id, service_id, remained_visits, " +
            "expiry, paid  FROM fitness_club.payment WHERE user_id = ?";

    public static final String MONEY_TRANSFER = "Query";

    public static final String BUY_PAYMENT = "UPDATE fitness_club.payment SET paid = 1 WHERE payment_id = ?";


}
