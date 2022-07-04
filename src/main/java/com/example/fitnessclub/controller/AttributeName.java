package com.example.fitnessclub.controller;

public final class AttributeName {

    private AttributeName() {
    }

    //web
    public static final String STEP_NUMBER = "step_number";
    public static final String STEP_ONE = "step_one";
    public static final String STEP_TWO = "step_two";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String USER_PARAM = "param_user";
    public static final String SERVICES = "services";
    public static final String LOCALE = "locale";
    public static final String CURRENT_PAGE = "current_page";
    public static final String TEMP_ATTRIBUTE = "temp_attribute";
    public static final String PAGE = "page";
    public static final String COMMAND = "command";
    public static final String SERVICE_ID_CART = "service_id_cart";
    public static final String IMAGE_PATH = "imagePath";
    public static final String DATE = "date";
    public static final String ADMIN_SWITCH = "admin_switch";
    public static final String TRAINER_SWITCH = "trainer_switch";
    public static final String TRAINER = "trainer";
    public static final String CLIENT = "client";
    public static final String CLIENTS = "clients";
    public static final String COST = "cost";

    //table users
    public static final String USER_ID = "user_id";
    public static final String ROLE = "role";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String REPEAT_PASSWORD = "repeat_password";
    public static final String REPLACE_PASSWORD = "replace_password";
    public static final String MAIL = "mail";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String DATE_BIRTH = "date_birth";
    public static final String SEX = "sex";
    public static final String PHONE = "phone";
    public static final String CORPORATE = "corporate";
    public static final String VISIT_PERIOD_MONTHS = "visit_period_months";
    public static final String DISCOUNT = "discount";
    public static final String NUMBER_CARD = "number_card";
    public static final String PATH_AVATAR = "path_avatar";
    public static final String ABOUT_ME = "about_me";
    public static final String TRAINERS = "trainers";
    public static final String IS_BLOCKED = "is_blocked";
    public static final String CASH = "cash";

    //table services
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_NAME = "service_name";
    public static final String NUMBER_VISITS = "number_visits";
    public static final String VALIDITY_PERIOD = "validity_period";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    //table payment
    public static final String PAYMENT = "payment";
    public static final String PAYMENTS = "payments";
    public static final String PAYMENT_STATUS = "payment_status";
    public static final String PAYMENT_ID = "payment_id";
    public static final String REMAINED_VISITS = "remained_visits";
    public static final String EXPIRY = "expiry";
    public static final String PAID = "paid";

    //appointment + List<exercise>
    public static final String WORKOUTS = "workouts";
    public static final String WORKOUT = "workout";
    public static final String WORKOUTS_BY_LOGIN = "workouts_by_login";

    //table appointment
    public static final String APPOINTMENT_ID = "appointment_id";
    public static final String APPOINTMENT_TYPE = "type_appointment";
    public static final String NUTRITION = "nutrition";
    public static final String APPOINTMENT_USER_ID = "user_id";

    //table exercise
    public static final String EXERCISE_ID = "exercise_id";
    public static final String EXERCISE_NAME = "name";
    public static final String NUMBER_SETS = "number_sets";
    public static final String NUMBER_REPETITIONS = "number_repetitions";
    public static final String EQUIPMENT = "equipment";

    //table contract
    public static final String CONTRACT_ID = "contract_id";
    public static final String TOTAL_COST = "total_cost";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String TRAINER_ID = "trainer_id";
    public static final String CLIENT_ID = "client_id";

}
