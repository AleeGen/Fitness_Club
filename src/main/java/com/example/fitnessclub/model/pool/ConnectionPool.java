package com.example.fitnessclub.model.pool;

import com.example.fitnessclub.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {

    private static int CAPACITY_POOL = 5;
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> free = new LinkedBlockingQueue<>(CAPACITY_POOL);
    private BlockingQueue<ProxyConnection> used = new LinkedBlockingQueue<>(CAPACITY_POOL);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    private ConnectionPool() {
        String url = "jdbc:mysql://localhost:3306/fitness_club";
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
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e.getMessage());
            }
            free.add(new ProxyConnection(connection));
        }
    }

    public static ConnectionPool getInstance() { //// TODO: 18.04.2022 потокобезопасным сделать, локи и тд
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = free.take();
            used.put(proxyConnection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection instanceof ProxyConnection) {
            try {
                used.remove(connection);
                free.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                //log
                Thread.currentThread().interrupt();
            }
        }
    }

    //// TODO: 23.04.2022 deregisterDriver(){} нужно (первые ссылки в инете)

    public void destroyPool() {
        for (int i = 0; i < CAPACITY_POOL; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
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
