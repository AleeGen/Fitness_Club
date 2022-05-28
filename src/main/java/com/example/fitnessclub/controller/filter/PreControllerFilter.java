/*
package com.example.fitnessclub.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Enumeration;

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        //logger.log(Level.INFO, "filter precontroller: /controller");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}

*/
