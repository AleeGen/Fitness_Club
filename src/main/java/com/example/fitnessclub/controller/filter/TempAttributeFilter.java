package com.example.fitnessclub.controller.filter;

import com.example.fitnessclub.controller.AttributeName;
import com.example.fitnessclub.controller.MessagePage;
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
        HashMap temp = (HashMap) request.getSession().getAttribute(AttributeName.TEMP_ATTRIBUTE);
        HashMap result = new HashMap();
        switch (currentPage) {
            case (PagePath.REGISTRATION_STEP_TWO), (PagePath.REGISTRATION_STEP_ONE),
                    (PagePath.PROFILE), (PagePath.PROFILE_EDIT):
                result.put(AttributeName.USER, temp.get(AttributeName.USER));
                break;
            case ("pages/test.jsp"):
                result.put(AttributeName.APPOINTMENTS, temp.get(AttributeName.APPOINTMENTS));
                break;
            default:
                logger.log(Level.INFO, "delete all temp attribute");
        }
        temp.clear();
        temp.putAll(result);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
