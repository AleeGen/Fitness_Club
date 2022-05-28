/*
package com.example.fitnessclub;

import com.example.fitnessclub.entity.AbstractEntity;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.exception.DaoException;
import com.example.fitnessclub.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

public class HelpBlinov2 { //// TODO: 23.04.2022 https://www.guru99.com/learn-sql-injection-with-practical-example.html#2
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "sqldata/database.properties";
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL = "/////////";
    private static String fileProperties;

    static {
        try {
            ClassLoader loader = ConnectionPool.class.getClassLoader();
            URL resource = loader.getResource(FILE_NAME);
            if (resource == null) {
                logger.log(Level.ERROR, "Resource is null! " + FILE_NAME);
                throw new IllegalArgumentException();
            }
            fileProperties = resource.getFile();
            properties.load(new FileReader(fileProperties));
            String driverName = (String) properties.get("db.driver");
        } catch (Exception e) {

        }
    }

    //------------------





    //------------------
    try(
    InputStream inputStream = ConnectionPool.class.getClassLoader()
            .getResourceAsStream("database.properties"))

    {
        properties.load(inputStream);

    }*/
