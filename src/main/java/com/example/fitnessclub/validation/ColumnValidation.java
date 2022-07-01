package com.example.fitnessclub.validation;

public final class ColumnValidation {

    private ColumnValidation() {
    }

    public static final String ROLE = "(admin)|(trainer)|(client)";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}$";
    public static final String REGEX_LOGIN = "^[a-zA-Z0-9._-]{3,15}$";
    public static final String REGEX_MAIL = "^[A-Za-z0-9_.-]+@[A-Za-z0-9.\\-_]+$";
    public static final String REGEX_NAME = "^\\p{L}{2,15}$";
    public static final String REGEX_LASTNAME = "^\\p{L}{2,15}$";
    public static final String REGEX_DATE = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String REGEX_SEX = "^(male)|(female)$";
    public static final String REGEX_PHONE = "^\\+?[0-9]{5,15}$";
    public static final String REGEX_CORPORATE = "(true)|(false)$";
    public static final String REGEX_DISCOUNT_CODE = "^[0-9A-Z]{10}$";
    public static final String REGEX_NUMBER_CARD = "^[0-9]{13,20}$";
}
