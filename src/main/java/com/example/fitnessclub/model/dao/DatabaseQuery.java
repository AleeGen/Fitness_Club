package com.example.fitnessclub.model.dao;

public final class DatabaseQuery {

    private DatabaseQuery() {
    }

    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT user_id FROM fitness_club.users WHERE login = ?";

    public static final String SELECT_ROLE_BY_LOGIN_PASSWORD = "SELECT role FROM fitness_club.users WHERE login = ? and password = ?";

    public static final String SELECT_USER_ALL_BY_LOGIN = "SELECT user_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card, path_avatar FROM fitness_club.users WHERE login = ?";

    public static final String INSERT_USER = "INSERT INTO fitness_club.users (login, password, mail, name, " +
            "lastname, date_birth, sex, phone,number_card) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ALL_SERVICES = "SELECT service_id, service_name, number_visits, validity_period, " +
            "price, description FROM fitness_club.services";

    public static final String SELECT_SERVICE_BY_ID = "SELECT service_id, service_name, number_visits, validity_period, " +
            "price, description FROM fitness_club.services WHERE service_id = ?";

    public static final String INSERT_USERS_HAS_SERVICES = "INSERT INTO fitness_club.users_has_services (user_id, " +
            "service_id, remained_visits, paid) VALUES (?,?,?,?)";

    public static final String UPDATE_USER_AVATAR = "UPDATE fitness_club.users SET path_avatar = ? WHERE login = ?";

    public static final String UPDATE_USER = "UPDATE fitness_club.users SET mail = ?," +
            "name = ?, lastname = ?, date_birth = ?, sex = ?, phone = ?, number_card = ? WHERE login = ?";

    public static final String SELECT_PASSWORD = "SELECT password FROM fitness_club.users WHERE login = ?";

    public static final String UPDATE_PASSWORD = "UPDATE fitness_club.users SET password = ? WHERE login = ?";

    public static final String SELECT_APPOINTMENTS_BY_USER_ID = "SELECT appointment_id, date, type_appointment " +
            "FROM fitness_club.appointment WHERE users_users_id = ?";

    public static final String SELECT_APPOINTMENTS_DESCRIPTION_BY_ID = "SELECT appointment_description_id, " +
            "exercise_name, number_sets, number_repetitions, equipment, nutrition, run_time, appointment_id" +
            " FROM fitness_club.appointment_description WHERE appointment_id = ?";
}
