package com.example.fitnessclub.model.service.mail;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SessionFactory {

    private SessionFactory(){}

    private static final String NAME_PROP = "mail.user.name";
    private static final String PASSWORD_PROP = "mail.user.password";

    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(NAME_PROP);
        String userPassword = configProperties.getProperty(PASSWORD_PROP);
        return Session.getDefaultInstance(configProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}