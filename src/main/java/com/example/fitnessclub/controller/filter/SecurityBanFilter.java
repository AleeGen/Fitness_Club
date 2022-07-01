package com.example.fitnessclub.controller.filter;

import com.example.fitnessclub.controller.AttributeName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.fitnessclub.model.entity.UserRole.ADMIN;

@WebFilter(filterName = "SecurityBanFilter", urlPatterns = {
        "/pages/person/admin/*", "/pages/header/*", "/pages/footer/*", "/pages/error/*"},
        initParams = {@WebInitParam(name = "TRANSITION", value = "/index.jsp")})
public class SecurityBanFilter implements Filter {

    private String transition;

    @Override
    public void init(FilterConfig config) {
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

