package com.example.fitnessclub.controller.filter;

import com.example.fitnessclub.controller.AttributeName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "SecurityEntryFilter", urlPatterns = {"/pages/person/*"},
        initParams = {@WebInitParam(name = "TRANSITION", value = "/pages/common/logIn.jsp")})
public class SecurityEntryFilter implements Filter {

    private String transition;

    @Override
    public void init(FilterConfig config) {
        transition = config.getInitParameter("TRANSITION");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) request).getSession().getAttribute(AttributeName.ROLE) == null) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + transition);
        }
        chain.doFilter(request, response);
    }

}

