package com.example.fitnessclub.controller.listener;

import com.example.fitnessclub.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        //ConnectionPool.getInstance();
        logger.log(Level.INFO, "|||++++++++++> context initialized: " + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        //ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "|||----------> context destroyed: " + sce.getServletContext().getContextPath());
    }

}
