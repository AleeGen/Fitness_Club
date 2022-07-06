package com.example.fitnessclub.model.entity;

import com.example.fitnessclub.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimerTask;

public class Updater extends TimerTask {

    private static final Logger logger = LogManager.getLogger();
    public static final String UPDATE_PAYMENT_PERIOD = "UPDATE fitness_club.payment SET paid = 0 WHERE NOW() > expiry";

    @Override
    public void run() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PAYMENT_PERIOD)) {
            if (statement.executeUpdate() != 0) {
                logger.log(Level.INFO, "The payment table has been updated");
            } else {
                logger.log(Level.WARN, "The payment table has NOT been updated");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception when periodically updating the payment table:\t", e);
        }
    }
}
