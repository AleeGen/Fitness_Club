package com.example.fitnessclub.constants;

public class MassagePage {
    public static final String VALIDATION_LOGIN_PASSWORD = """
            Incorrect login or password:\s
            The length of the login must be from 3 to 15 characters. Allowed characters: letters, numbers and symbols ".-_".
            The length of the password must be from 8 to 25 characters and must contain a number, a letter, a capital letter. Space characters are prohibited.
            """;
    public static final String REGISTRATION_SUCCESSFUL = "Registration was successful";
    public static final String USER_EXISTS = "user already exists";
    public static final String INCORRECT_LOGIN_PASSWORD = "incorrect login or password";

}
