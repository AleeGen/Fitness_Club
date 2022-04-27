package com.example.fitnessclub;

public enum HelpBlinov {
    SELECT_LOGIN_PASSWORD("SELECT password FROM users WHERE lastname = ?"),
    UPDATE_LOGIN_PASSWORD("SELECT password FROM users WHERE lastname = ?")
    ;
    private String sql;

    HelpBlinov(String sql) {
        this.sql = sql;
    }
}
interface SqlBadUsing {
    String SELECT_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";
    String UPDATE_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";
}

enum SqlQuery {
    ;
    public static final String SELECT_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";
    public static final String UPDATE_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";
}
final class Sql {
    public static final String SELECT_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";
    public static final String UPDATE_LOGIN_PASSWORD="SELECT password FROM users WHERE lastname = ?";

    private Sql(){}
}
