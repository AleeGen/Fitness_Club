package com.example.fitnessclub.validation;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.RequestParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationUser {

    private static final String COLOR = "_color";

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

    public boolean isValidRegistrationStepOne(RequestParameters param) {
        if (param == null) {
            return false;
        }
        boolean isValid = true;
        if (param.get(AttributeName.LOGIN).equals("")) {
            isValid = false;
            param.put(AttributeName.LOGIN, TypeInvalid.OBLIGATORY_FIELD);
            param.put(AttributeName.LOGIN + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidLogin(param.get(AttributeName.LOGIN))) {
            isValid = false;
            param.put(AttributeName.LOGIN, TypeInvalid.INVALID_LOGIN);
            param.put(AttributeName.LOGIN + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(AttributeName.REPEAT_PASSWORD).equals(param.get(AttributeName.PASSWORD))) {
            if (param.get(AttributeName.PASSWORD).equals("")) {
                isValid = false;
                param.put(AttributeName.PASSWORD, TypeInvalid.OBLIGATORY_FIELD);
                param.put(AttributeName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
            } else if (!isValidPassword(param.get(AttributeName.PASSWORD))) {
                isValid = false;
                param.put(AttributeName.PASSWORD, TypeInvalid.INVALID_PASSWORD);
                param.put(AttributeName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
            }
        } else {
            isValid = false;
            param.put(AttributeName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
            param.put(AttributeName.REPEAT_PASSWORD, TypeInvalid.INVALID_REPEAT_PASSWORD);
            param.put(AttributeName.REPEAT_PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
        }


        if (param.get(AttributeName.MAIL).equals("")) {
            isValid = false;
            param.put(AttributeName.MAIL, TypeInvalid.OBLIGATORY_FIELD);
            param.put(AttributeName.MAIL + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidMail(param.get(AttributeName.MAIL))) {
            isValid = false;
            param.put(AttributeName.MAIL, TypeInvalid.INVALID_EMAIL);
            param.put(AttributeName.MAIL + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(AttributeName.NAME).equals("")) {
            isValid = false;
            param.put(AttributeName.NAME, TypeInvalid.OBLIGATORY_FIELD);
            param.put(AttributeName.NAME + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidName(param.get(AttributeName.NAME))) {
            isValid = false;
            param.put(AttributeName.NAME, TypeInvalid.INVALID_NAME);
            param.put(AttributeName.NAME + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(AttributeName.LASTNAME).equals("")) {
            isValid = false;
            param.put(AttributeName.LASTNAME, TypeInvalid.OBLIGATORY_FIELD);
            param.put(AttributeName.LASTNAME + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidLastname(param.get(AttributeName.LASTNAME))) {
            isValid = false;
            param.put(AttributeName.LASTNAME, TypeInvalid.INVALID_LASTNAME);
            param.put(AttributeName.LASTNAME + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (!isValid) { //// TODO: 29.05.2022 подумать над сообщениями, есть todo в классе TypeInvalid
            param.put(AttributeName.PASSWORD, "");
            param.put(AttributeName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
            param.put(AttributeName.REPEAT_PASSWORD, "");
            param.put(AttributeName.REPEAT_PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
        }
        return isValid;
    }

    public boolean isValidRegistrationStepTwo(RequestParameters param) {
        if (param == null) {
            return false;
        }
        boolean isValid = true;
        System.out.println("v1");
        if (!param.get(AttributeName.PHONE).equals("") && !isValidPhone(param.get(AttributeName.PHONE))) {
            isValid = false;
            param.put(AttributeName.PHONE, TypeInvalid.INVALID_PHONE);
            param.put(AttributeName.PHONE + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(AttributeName.PHONE + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v2");
        if (!param.get(AttributeName.DATE_BIRTH).equals("") && !isValidDate(param.get(AttributeName.DATE_BIRTH))) {
            isValid = false;
            param.put(AttributeName.DATE_BIRTH, TypeInvalid.INVALID_DATE);
            param.put(AttributeName.DATE_BIRTH + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(AttributeName.DATE_BIRTH + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v3");
        if (param.get(AttributeName.SEX) != null) {
            if (!param.get(AttributeName.SEX).equals("") && !isValidSex(param.get(AttributeName.SEX))) {
                isValid = false;
                param.put(AttributeName.SEX, TypeInvalid.INVALID_SEX);
                param.put(AttributeName.SEX + COLOR, TypeInvalid.COLOR_INVALID);
            }
        }
        System.out.println("v4");
        if (!param.get(AttributeName.NUMBER_CARD).equals("") && !isValidNumberCard(param.get(AttributeName.NUMBER_CARD))) {
            isValid = false;
            param.put(AttributeName.NUMBER_CARD, TypeInvalid.INVALID_NUMBER_CARD);
            param.put(AttributeName.NUMBER_CARD + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(AttributeName.NUMBER_CARD + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v5");
        return isValid;
    }
}
