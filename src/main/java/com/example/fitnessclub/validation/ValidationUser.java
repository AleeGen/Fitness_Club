package com.example.fitnessclub.validation;

import com.example.fitnessclub.constants.ColumnValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationUser {

    public boolean isValidLogin(String login) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_LOGIN).matcher(login);
        return pattern.matches();
    }

    public boolean isValidPassword(String password) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_PASSWORD).matcher(password);
        return pattern.matches();
    }

    public boolean isValidMail(String mail) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_MAIL).matcher(mail);
        return pattern.matches();
    }

    public boolean isValidName(String name) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_NAME).matcher(name);
        return pattern.matches();
    }

    public boolean isValidLastname(String lastname) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_LASTNAME).matcher(lastname);
        return pattern.matches();
    }

    public boolean isValidDate(String date) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_DATE).matcher(date);
        return pattern.matches();
    }

    public boolean isValidSex(String sex) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_SEX).matcher(sex);
        return pattern.matches();
    }

    public boolean isValidPhone(String phone) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_PHONE).matcher(phone);
        return pattern.matches();
    }

    public boolean isValidNumberCard(String numberCard) {
        Matcher pattern = Pattern.compile(ColumnValidation.REGEX_NUMBER_CARD).matcher(numberCard);
        return pattern.matches();
    }
}
