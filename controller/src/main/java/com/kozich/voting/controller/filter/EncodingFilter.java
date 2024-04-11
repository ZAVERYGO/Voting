package com.kozich.voting.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String requestURL = req.getHttpServletMapping().getPattern();

        request.setCharacterEncoding("UTF-8");
        if(requestURL.startsWith("/api")){
            response.setContentType("application/json; charset=UTF-8");
        } else {
            response.setContentType("text/html; charset=UTF-8");
        }

        chain.doFilter(request, response);
    }
}
