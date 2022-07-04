package com.example.fitnessclub.validator;

import com.example.fitnessclub.validation.ValidationUser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidatorTest {

    private final ValidationUser validator = ValidationUser.getInstance();

    @DataProvider(name = "validation_login")
    public Object[][] createDataLogin() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"", false};
        data[1] = new Object[]{"AcsX", true};
        data[2] = new Object[]{"_s2fa0", true};
        data[3] = new Object[]{"asd Fs", false};
        data[4] = new Object[]{"vAda123qwezxcaAc24ascac", false};
        return data;
    }

    @DataProvider(name = "validation_role")
    public Object[][] createDataRole() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"trainer", true};
        data[1] = new Object[]{"admin", true};
        data[2] = new Object[]{"client", true};
        data[3] = new Object[]{"human", false};
        data[4] = new Object[]{"", false};
        return data;
    }

    @DataProvider(name = "validation_password")
    public Object[][] createDataPassword() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"12345qwerty", false};
        data[1] = new Object[]{"12345Qwerty", true};
        data[2] = new Object[]{"qwe", false};
        data[3] = new Object[]{"45-vasA52.*a", true};
        data[4] = new Object[]{"", false};
        return data;
    }

    @DataProvider(name = "validation_mail")
    public Object[][] createDataMail() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"human@gmail.com", true};
        data[1] = new Object[]{"asd@", false};
        data[2] = new Object[]{"@val.ru", false};
        data[3] = new Object[]{"as@mail.ru", true};
        data[4] = new Object[]{"Afv @ mail.com", false};
        return data;
    }

    @DataProvider(name = "validation_name")
    public Object[][] createDataName() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"Alexey", true};
        data[1] = new Object[]{"Петр", true};
        data[2] = new Object[]{"Петр1", false};
        data[3] = new Object[]{"А", false};
        data[4] = new Object[]{"qweasdzxcrtyfghv", false};
        return data;
    }

    @DataProvider(name = "validation_lastname")
    public Object[][] createDataLastname() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"Петров", true};
        data[1] = new Object[]{"Li", true};
        data[2] = new Object[]{"Fs3", false};
        data[3] = new Object[]{"А", false};
        data[4] = new Object[]{"qweasdzxcrtyfghv", false};
        return data;
    }

    @DataProvider(name = "validation_date")
    public Object[][] createDataDate() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"2020-12-12", true};
        data[1] = new Object[]{"19-1-2", false};
        data[2] = new Object[]{"2020-13-05", false};
        data[3] = new Object[]{"1985-04-32", false};
        data[4] = new Object[]{"1985-1-14", true};
        return data;
    }

    @DataProvider(name = "validation_sex")
    public Object[][] createDataSex() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"male", true};
        data[1] = new Object[]{"female", true};
        data[2] = new Object[]{"1", false};
        data[3] = new Object[]{"0", false};
        data[4] = new Object[]{"human", false};
        return data;
    }

    @DataProvider(name = "validation_phone")
    public Object[][] createDataPhone() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"24756", true};
        data[1] = new Object[]{"1234", false};
        data[2] = new Object[]{"1234567890123456", false};
        data[3] = new Object[]{"80291234567", true};
        data[4] = new Object[]{"8029A2564373", false};
        return data;
    }

    @DataProvider(name = "validation_corporate")
    public Object[][] createDataCorporate() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"true", true};
        data[1] = new Object[]{"false", true};
        data[2] = new Object[]{"yes", false};
        data[3] = new Object[]{"no", false};
        data[4] = new Object[]{"", false};
        return data;
    }

    @DataProvider(name = "validation_discount")
    public Object[][] createDataDiscount() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"100", true};
        data[1] = new Object[]{"101", false};
        data[2] = new Object[]{"32", true};
        data[3] = new Object[]{"af", false};
        data[4] = new Object[]{"-30", false};
        return data;
    }

    @DataProvider(name = "validation_number_card")
    public Object[][] createDataNumberCard() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"1234567890123", true};
        data[1] = new Object[]{"12345678901234567890", true};
        data[2] = new Object[]{"123456789012", false};
        data[3] = new Object[]{"123456789012345678901", false};
        data[4] = new Object[]{"as1234567890123", false};
        return data;
    }

    @DataProvider(name = "validation_cash")
    public Object[][] createDataCash() {
        Object[][] data = new Object[5][2];
        data[0] = new Object[]{"-12", false};
        data[1] = new Object[]{"0", false};
        data[2] = new Object[]{"12372", true};
        data[3] = new Object[]{"05", false};
        data[4] = new Object[]{"1a0", false};
        return data;
    }


    @Test(dataProvider = "validation_login")
    public void testValidationLogin(String str, boolean expected) {
        boolean actual = validator.isValidLogin(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_role")
    public void testValidationRole(String str, boolean expected) {
        boolean actual = validator.isValidRole(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_password")
    public void testValidationPassword(String str, boolean expected) {
        boolean actual = validator.isValidPassword(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_mail")
    public void testValidationMail(String str, boolean expected) {
        boolean actual = validator.isValidMail(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_name")
    public void testValidationName(String str, boolean expected) {
        boolean actual = validator.isValidName(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_lastname")
    public void testValidationLastname(String str, boolean expected) {
        boolean actual = validator.isValidLastname(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_date")
    public void testValidationDate(String str, boolean expected) {
        boolean actual = validator.isValidDate(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_sex")
    public void testValidationSex(String str, boolean expected) {
        boolean actual = validator.isValidSex(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_phone")
    public void testValidationPhone(String str, boolean expected) {
        boolean actual = validator.isValidPhone(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_corporate")
    public void testValidationCorporate(String str, boolean expected) {
        boolean actual = validator.isValidCorporate(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_discount")
    public void testValidationDiscount(String str, boolean expected) {
        boolean actual = validator.isValidDiscount(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_number_card")
    public void testValidationNumberCarD(String str, boolean expected) {
        boolean actual = validator.isValidNumberCard(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }

    @Test(dataProvider = "validation_cash")
    public void testValidationCashD(String str, boolean expected) {
        boolean actual = validator.isValidCash(str);
        assertEquals(actual, expected, str + " - Invalid!");
    }
}
