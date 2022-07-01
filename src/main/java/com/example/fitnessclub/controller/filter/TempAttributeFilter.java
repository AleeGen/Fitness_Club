package com.example.fitnessclub.controller.filter;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.PagePath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.FORWARD, filterName = "TempAttributeFilter")
public class TempAttributeFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String currentPage = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        HashMap<String, Object> tempAttr = (HashMap<String, Object>) request.getSession().getAttribute(AttributeName.TEMP_ATTRIBUTE);
        HashMap<String, Object> result = new HashMap<>();
        switch (currentPage) {
            case (PagePath.REGISTRATION_STEP_TWO), (PagePath.REGISTRATION_STEP_ONE), (PagePath.PROFILE), (PagePath.PROFILE_EDIT) -> result.put(AttributeName.USER, tempAttr.get(AttributeName.USER));
            case (PagePath.APPOINTMENTS) -> {
                result.put(AttributeName.WORKOUTS, tempAttr.get(AttributeName.WORKOUTS));
                result.put(AttributeName.WORKOUTS_BY_LOGIN, tempAttr.get(AttributeName.WORKOUTS_BY_LOGIN));
            }
            case (PagePath.LIST_USERS) -> result.put(AttributeName.USERS, tempAttr.get(AttributeName.USERS));
            case (PagePath.PAYMENTS) -> {
                result.put(AttributeName.PAYMENTS, tempAttr.get(AttributeName.PAYMENTS));
                result.put(AttributeName.PAID, tempAttr.get(AttributeName.PAID));
            }
            case (PagePath.EDIT_APPOINTMENT) -> {
                result.put(AttributeName.WORKOUTS, tempAttr.get(AttributeName.WORKOUTS));
                result.put(AttributeName.WORKOUT, tempAttr.get(AttributeName.WORKOUT));
                result.put(AttributeName.WORKOUTS_BY_LOGIN, tempAttr.get(AttributeName.WORKOUTS_BY_LOGIN));
            }
            default -> logger.log(Level.INFO, "delete all tempAttr attribute");
        }
        tempAttr.clear();
        tempAttr.putAll(result);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
