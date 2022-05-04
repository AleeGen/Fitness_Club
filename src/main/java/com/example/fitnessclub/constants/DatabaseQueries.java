package com.example.fitnessclub.constants;

public class DatabaseQueries {
    public static final String SELECT_USER_BY_LOGIN = "SELECT login FROM users WHERE login = ?";
    public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String INSERT_USER = "INSERT INTO users (login, password,mail, name, " +
            "lastname, date_birth, sex, phone,number_card) VALUES (?,?,?,?,?,?,?,?,?)";

}
