package com.example.fitnessclub.model.service.mail;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.RequestParameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailMain {

    private static final String PATH_CONFIG_MAIL = "config/mail.properties";
    private static final String SUBJECT = "Registration on the \"Fitness club\"";
    private static final String BODY_PATTERN = "Welcome, %1$s %2$s (%3$s)! You have registered in a fitness club";

    public static void sendTo(RequestParameters user) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = MailMain.class.getClassLoader().getResourceAsStream(PATH_CONFIG_MAIL);
        properties.load(inputStream);
        String lastname = user.get(AttributeName.LASTNAME);
        String name = user.get(AttributeName.NAME);
        String login = user.get(AttributeName.LOGIN);
        String body = String.format(BODY_PATTERN, lastname, name, login);
        String emailTo = user.get(AttributeName.MAIL);
        MailSender sender = new MailSender(emailTo, SUBJECT, body, properties);
        sender.send();
    }
}
