/*
package com.example.fitnessclub.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //logger.log(Level.INFO, "filter encoding: /*");
        String codeRequest = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        code = null;
    }
}*/
