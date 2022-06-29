package com.example.fitnessclub.controller.filter;

import com.example.fitnessclub.controller.AttributeName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

import static com.example.fitnessclub.model.entity.UserRole.ADMIN;

@WebFilter(filterName = "SecurityBanFilter", urlPatterns = {
        "/pages/person/admin/*", "/pages/header/*", "/pages/footer/*", "/pages/error/*"},
        initParams = {@WebInitParam(name = "TRANSITION", value = "/index.jsp")})
public class SecurityBanFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    private String transition;

    public void init(FilterConfig config) throws ServletException {
        transition = config.getInitParameter("TRANSITION");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) request).getSession().getAttribute(AttributeName.ROLE) != ADMIN) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + transition);
        }
        chain.doFilter(request, response);
    }

}

