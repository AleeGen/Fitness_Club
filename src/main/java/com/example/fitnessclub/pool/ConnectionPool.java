package com.example.fitnessclub.pool;

import com.example.fitnessclub.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static int CAPACITY_POOL = 8;
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> free = new LinkedBlockingQueue<>(CAPACITY_POOL);
    private BlockingQueue<ProxyConnection> used = new LinkedBlockingQueue<>(CAPACITY_POOL);

    //// TODO: 24.04.2022 неуверен с ProxyConnection, правильно ли везде я его подобавлял 
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    //// TODO: 19.04.2022 познакомиться с Connection
    //// TODO: 19.04.2022 будем использовать прокси коннекшен
    private ConnectionPool() {  //// TODO: 19.04.2022 конструктор вызывается только один раз при обращении к пулу? там ниже в цикле мы создаем 8 конекшнов свободных, по ходу да, вызывается в самом начале... наверное
        String url = "jdbc:mysql://localhost:3306/fonestest";
        Properties prop = new Properties(); //// TODO: 19.04.2022 из проперти должны читаться все эти строки
        prop.put("user", "root");
        prop.put("password", "aleegen");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");
        for (int i = 0; i < CAPACITY_POOL; i++) {
            ProxyConnection connection = null;
            try {
                connection = (ProxyConnection) DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e.getMessage());
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() { //// TODO: 18.04.2022 потокобезопасным сделать, локи и тд
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection.getClass() != ProxyConnection.class) {
            throw new ConnectionPoolException();
        }
        try {
            used.remove(connection);
            free.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
    }

    //// TODO: 23.04.2022 deregisterDriver(){} нужно (первые ссылки в инете)

    public void destroyPool() {
        for (int i = 0; i < CAPACITY_POOL; i++) {
            try {
                free.take().reallyClose();
            } catch (InterruptedException e) {
                //log
            }
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                //log
            }
        });
    }
}
