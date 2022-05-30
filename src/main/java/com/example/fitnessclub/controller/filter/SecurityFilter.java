package com.example.fitnessclub.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/pages/*"},
        initParams = {@WebInitParam(name = "MAIN_PATH", value = "/main.jsp")})
public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    private String indexPath;

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter("MAIN_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.log(Level.INFO, "filter security: /pages/*");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}

