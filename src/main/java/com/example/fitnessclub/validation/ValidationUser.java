package com.example.fitnessclub.validation;

import com.example.fitnessclub.model.mapper.ColumnName;
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
        if (param.get(ColumnName.LOGIN).equals("")) {
            isValid = false;
            param.put(ColumnName.LOGIN, TypeInvalid.OBLIGATORY_FIELD);
            param.put(ColumnName.LOGIN + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidLogin(param.get(ColumnName.LOGIN))) {
            isValid = false;
            param.put(ColumnName.LOGIN, TypeInvalid.INVALID_LOGIN);
            param.put(ColumnName.LOGIN + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(ColumnName.PASSWORD).equals("")) {
            isValid = false;
            param.put(ColumnName.PASSWORD, TypeInvalid.OBLIGATORY_FIELD);
            param.put(ColumnName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidPassword(param.get(ColumnName.PASSWORD))) {
            isValid = false;
            param.put(ColumnName.PASSWORD, TypeInvalid.INVALID_PASSWORD);
            param.put(ColumnName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(ColumnName.MAIL).equals("")) {
            isValid = false;
            param.put(ColumnName.MAIL, TypeInvalid.OBLIGATORY_FIELD);
            param.put(ColumnName.MAIL + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidMail(param.get(ColumnName.MAIL))) {
            isValid = false;
            param.put(ColumnName.MAIL, TypeInvalid.INVALID_EMAIL);
            param.put(ColumnName.MAIL + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(ColumnName.NAME).equals("")) {
            isValid = false;
            param.put(ColumnName.NAME, TypeInvalid.OBLIGATORY_FIELD);
            param.put(ColumnName.NAME + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidName(param.get(ColumnName.NAME))) {
            isValid = false;
            param.put(ColumnName.NAME, TypeInvalid.INVALID_NAME);
            param.put(ColumnName.NAME + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (param.get(ColumnName.LASTNAME).equals("")) {
            isValid = false;
            param.put(ColumnName.LASTNAME, TypeInvalid.OBLIGATORY_FIELD);
            param.put(ColumnName.LASTNAME + COLOR, TypeInvalid.COLOR_INVALID);
        } else if (!isValidLastname(param.get(ColumnName.LASTNAME))) {
            isValid = false;
            param.put(ColumnName.LASTNAME, TypeInvalid.INVALID_LASTNAME);
            param.put(ColumnName.LASTNAME + COLOR, TypeInvalid.COLOR_INVALID);
        }

        if (!isValid) {
            param.put(ColumnName.PASSWORD, "");
            param.put(ColumnName.PASSWORD + COLOR, TypeInvalid.COLOR_INVALID);
        }
        return isValid;
    }

    public boolean isValidRegistrationStepTwo(RequestParameters param) {
        if (param == null) {
            return false;
        }
        boolean isValid = true;
        System.out.println("v1");
        if (!param.get(ColumnName.PHONE).equals("") && !isValidPhone(param.get(ColumnName.PHONE))) {
            isValid = false;
            param.put(ColumnName.PHONE, TypeInvalid.INVALID_PHONE);
            param.put(ColumnName.PHONE + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(ColumnName.PHONE + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v2");
        if (!param.get(ColumnName.DATE_BIRTH).equals("") && !isValidDate(param.get(ColumnName.DATE_BIRTH))) {
            isValid = false;
            param.put(ColumnName.DATE_BIRTH, TypeInvalid.INVALID_DATE);
            param.put(ColumnName.DATE_BIRTH + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(ColumnName.DATE_BIRTH + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v3");
        if (param.get(ColumnName.SEX) != null) {
            if (!param.get(ColumnName.SEX).equals("") && !isValidSex(param.get(ColumnName.SEX))) {
                isValid = false;
                param.put(ColumnName.SEX, TypeInvalid.INVALID_SEX);
                param.put(ColumnName.SEX + COLOR, TypeInvalid.COLOR_INVALID);
            }
        }
        System.out.println("v4");
        if (!param.get(ColumnName.NUMBER_CARD).equals("") && !isValidNumberCard(param.get(ColumnName.NUMBER_CARD))) {
            isValid = false;
            param.put(ColumnName.NUMBER_CARD, TypeInvalid.INVALID_NUMBER_CARD);
            param.put(ColumnName.NUMBER_CARD + COLOR, TypeInvalid.COLOR_INVALID);
        } else {
            param.put(ColumnName.NUMBER_CARD + COLOR, TypeInvalid.COLOR_VALID);
        }
        System.out.println("v5");
        return isValid;
    }
}
