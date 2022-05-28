/*

package com.example.fitnessclub.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "ForwardFilter", dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = "/pages/*")
public class ForwardFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //logger.log(Level.INFO, "filter forward: /pages/*");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        session.setAttribute("filter_attr", "DispatcherType.FORWARD");
        chain.doFilter(request, response);
    }
}

*/
