package com.kozich.voting.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.voting.controller.factory.ControllerFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerFilter implements Filter {
    private final ObjectMapper mapper = ControllerFactory.getMapper();


    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        boolean error = false;
        int errorStatusCode = 0;
        String errorMessage = "Ошибка на стороне сервера";

        try{
            chain.doFilter(request, response);
        } catch (IllegalArgumentException e){
            error = true;
            errorStatusCode = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = e.getMessage();
        } catch (Exception e){
            error = true;
            errorStatusCode = HttpServletResponse.SC_BAD_REQUEST;
        } finally {
            if(error){
                if(response instanceof HttpServletResponse){
                    HttpServletResponse castResponse = (HttpServletResponse) response;

                    castResponse.setStatus(errorStatusCode);

                    PrintWriter writer = castResponse.getWriter();

                    if(isJsonResponse(castResponse)){

                        Map<String, Object> errorObj = new HashMap<>();
                        errorObj.put("error", errorMessage);

                        writer.write(mapper.writeValueAsString(errorObj));
                    } else {
                        writer.write(errorMessage);
                    }
                }
            }
        }
    }

    private boolean isJsonResponse(HttpServletResponse response){
        return response.getContentType().startsWith("application/json");
    }
}