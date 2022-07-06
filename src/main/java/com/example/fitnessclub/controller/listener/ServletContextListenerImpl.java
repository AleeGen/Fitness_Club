package com.example.fitnessclub.controller.listener;

import com.example.fitnessclub.model.entity.Updater;
import com.example.fitnessclub.model.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Timer;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();
    private static final Timer updater = new Timer();
    private static final int DELAY = 1000 * 10;
    private static final int PERIOD = 1000 * 30;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "Servlet context initialized");
        updater.schedule(new Updater(), DELAY, PERIOD);
        logger.log(Level.INFO, "Database updater started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "Servlet context destroyed");
        updater.cancel();
        logger.log(Level.INFO, "Database updater finished");
    }

}
