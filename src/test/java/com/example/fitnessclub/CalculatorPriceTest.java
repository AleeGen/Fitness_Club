package com.example.fitnessclub;

import com.example.fitnessclub.exception.ServiceException;
import com.example.fitnessclub.model.service.impl.ContractServiceImpl;
import com.example.fitnessclub.model.service.impl.PaymentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class CalculatorPriceTest {

    private static final Logger logger = LogManager.getLogger();

    @DataProvider(name = "calculator_service")
    public Object[][] createDataBuyService() {
        Object[][] data = new Object[4][3];
        data[0] = new Object[]{"leon", 1, 4};
        data[1] = new Object[]{"lika", 9, 21};
        data[2] = new Object[]{"leonov2", 2, 20};
        data[3] = new Object[]{"leonov3", 5, 18};
        System.out.println("1");
        return data;
    }

    @DataProvider(name = "calculator_contract")
    public Object[][] createDataBuyContract() {
        Object[][] data = new Object[4][4];
        data[0] = new Object[]{"2022-10-10", "2022-10-20", "leon", 80};
        data[1] = new Object[]{"2022-10-11", "2022-11-10", "lika", 210};
        data[2] = new Object[]{"2022-12-08", "2023-02-19", "leonov2",639};
        data[3] = new Object[]{"2022-07-22", "2023-04-15", "leonov3",2640};
        return data;
    }


    @Test(dataProvider = "calculator_service")
    public void testValidationBuyService(String login, int serviceId, int expected) {
        short actual = 0;
        try {
            Optional<Short> cost = PaymentServiceImpl.getInstance().calculateCost(login, serviceId);
            if (cost.isPresent()) {
                actual = cost.get();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(actual, expected, "Invalid!");
    }

    @Test(dataProvider = "calculator_contract")
    public void testValidationContract(String startDate, String endDate, String login, int expected) {
        short actual = 0;
        try {
            Optional<Short> cost = ContractServiceImpl.getInstance().calculate(startDate, endDate, login);
            if (cost.isPresent()) {
                actual = cost.get();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        assertEquals(actual, expected, "Invalid!");
    }
}
