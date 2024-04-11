package com.kozich.voting.controller.http;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.voting.controller.factory.ControllerFactory;
import com.kozich.voting.dao.api.dto.StatDTO;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.factory.ServiceFactorySingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StatServlet extends HttpServlet {

    private StatService statService = ServiceFactorySingleton.getStatService();
    private final ObjectMapper mapper = ControllerFactory.getMapper();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        StatDTO stat = this.statService.get();

        resp.getWriter().write(mapper.writeValueAsString(stat));
    }

}
