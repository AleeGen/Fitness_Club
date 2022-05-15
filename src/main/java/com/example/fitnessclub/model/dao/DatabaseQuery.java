package com.example.fitnessclub.model.dao;

public class DatabaseQuery {

    public static final String SELECT_USER_BY_LOGIN = "SELECT login FROM users WHERE login = ?";

    public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT users_id, login, password, role, mail, " +
            "name,lastname, date_birth, sex, phone, corporate, visit_period_months, discount_code, " +
            "number_card FROM fitness_club.users WHERE login = ? and password = ?";

    public static final String INSERT_USER = "INSERT INTO fitness_club.users (login, password,mail, name, " +
            "lastname, date_birth, sex, phone,number_card) VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ALL_SERVICES = "SELECT services_id, service_name, number_visits, " +
            "price, description FROM fitness_club.services";

}
