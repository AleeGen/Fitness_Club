package com.example.fitnessclub.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_PROPERTIES = "prop/database.properties";
    private static final String SIZE_POOL = "size_pool";
    private static final String URL = "url";
    private static final Properties property = new Properties();
    private static final int CAPACITY_POOL;
    private static ConnectionPool instance;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private final BlockingQueue<ProxyConnection> free = new LinkedBlockingQueue<>(CAPACITY_POOL);
    private final BlockingQueue<ProxyConnection> used = new LinkedBlockingQueue<>(CAPACITY_POOL);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.fatal("Not register driver", e);
            throw new ExceptionInInitializerError(e.getMessage());
        }
        try {
            InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(PATH_PROPERTIES);
            property.load(inputStream);
            CAPACITY_POOL = Integer.parseInt(property.getProperty(SIZE_POOL));
        } catch (IOException e) {
            logger.fatal("Properties not load", e);
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    private ConnectionPool() {
        for (int i = 0; i < CAPACITY_POOL; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection((String) property.get(URL), property);
            } catch (SQLException e) {
                logger.log(Level.WARN, "Exception when creating connection", e);
            }
            free.add(new ProxyConnection(connection));
        }
        if (free.size() < CAPACITY_POOL) {
            for (int i = 0; i < CAPACITY_POOL - free.size(); i++) {
                Connection connection;
                try {
                    connection = DriverManager.getConnection((String) property.get(URL), property);
                } catch (SQLException e) {
                    logger.log(Level.FATAL, "Connections was not created!", e);
                    throw new ExceptionInInitializerError(e.getMessage());
                }
                free.add(new ProxyConnection(connection));
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            if (instance == null) {
                try {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                    logger.log(Level.INFO, "ConnectionPool initialized");
                } finally {
                    lock.unlock();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = free.take();
            used.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Connection not release, thread interrupt", e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            try {
                if (used.remove(connection)) {
                    logger.log(Level.INFO, "Connection released");
                } else {
                    logger.log(Level.WARN, "The connection was not released");
                }
                free.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Connection not release, thread interrupt", e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.log(Level.ERROR, "Illegal connection!");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < CAPACITY_POOL; i++) {
            try {
                free.take().reallyClose();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Error when destroy pool", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.INFO, "Deregister driver");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error when deregister driver", e);
            }
        });
    }
}
