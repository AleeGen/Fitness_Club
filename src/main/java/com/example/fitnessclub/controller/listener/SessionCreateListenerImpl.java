package com.example.fitnessclub.controller.listener;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        logger.log(Level.INFO, "+++++++++> session created with id: " + se.getSession().getId());
        se.getSession().setAttribute(AttributeName.TEMP_ATTRIBUTE, new HashMap());
        se.getSession().setAttribute(AttributeName.CURRENT_PAGE, PagePath.INDEX);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        //logger.log(Level.INFO, "---------> session destroyed with id: " + se.getSession().getId());
    }

}
