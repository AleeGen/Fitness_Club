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

@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.FORWARD, filterName = "EditLocaleFilter")
public class TempSessionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.log(Level.INFO, "filter locale: /pages/service.jsp");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String currentPage = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        HashMap temp = (HashMap) request.getSession().getAttribute(AttributeName.TEMP_SESSION);
        HashMap result = new HashMap();
        switch (currentPage) {
            case (PagePath.REGISTRATION_STEP_TWO), (PagePath.REGISTRATION_STEP_ONE), (PagePath.MAIN):
                result.put(AttributeName.USER, temp.get(AttributeName.USER));
                break;
            case (PagePath.SERVICE):
                result.put(AttributeName.SERVICES, temp.get(AttributeName.SERVICES));
                break;
            default:
                logger.log(Level.INFO, "страница не удалила атрибуты сессии");
        }
        temp.clear();
        temp.putAll(result);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
