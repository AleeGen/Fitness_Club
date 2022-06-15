package com.example.fitnessclub.controller;

public final class MessagePage {

    private MessagePage(){}

    public static final String MESSAGE = "message";
    public static final String VALIDATION_LOGIN_PASSWORD = """
            Incorrect login or password:\s
            The length of the login must be from 3 to 15 characters. Allowed characters: letters, numbers and symbols ".-_".
            The length of the password must be from 8 to 25 characters and must contain a number, a letter, a capital letter. Space characters are prohibited.
            """;
    public static final String REGISTRATION_SUCCESSFUL = "Registration was successful";
    public static final String USER_EXISTS = "user already exists";
    public static final String INCORRECT_LOGIN_PASSWORD = "incorrect login or password";
    public static final String UNKNOWN_TRANSITION_ROUTER = "unknown transition router";
    public static final String ADDED_TO_CART = "ADDED to cart";
    public static final String NOT_ADDED_TO_CART ="NOT ADDED to cart, something went wrong";
    public static final String UPLOAD_SUCCESSFULLY = "upload successfully";
    public static final String UPLOAD_FAILED = "upload failed";
    public static final String EDIT_USER_SUCCESSFULLY = "edit user successfully";
    public static final String EDIT_USER_FAILED = "edit user failed";
    public static final String PASSWORD_CHANGED = "Password changed successfully";
    public static final String PASSWORD_NOT_CHANGED = "Failed to change password";
}
