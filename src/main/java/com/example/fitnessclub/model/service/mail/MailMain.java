package com.example.fitnessclub.model.service.mail;

import com.example.fitnessclub.model.pool.ConnectionPool;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class MailMain {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream("config/mail.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties);
        String mailTo = "leshaleonenko@mail.ru";
        String subject = "Sample Mail";
        String body = "Hello java mail";
        MailSender sender = new MailSender(mailTo, subject, body, properties);
        sender.send();
    }
}
