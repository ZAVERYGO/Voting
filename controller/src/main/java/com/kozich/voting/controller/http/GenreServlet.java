package com.kozich.voting.controller.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.voting.controller.factory.ControllerFactory;
import com.kozich.voting.service.api.GenreService;
import com.kozich.voting.service.api.dto.GenreDTO;
import com.kozich.voting.service.api.dto.VoteDTO;
import com.kozich.voting.service.factory.ServiceFactorySingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GenreServlet extends HttpServlet {


    private final ObjectMapper mapper = ControllerFactory.getMapper();
    private final GenreService genreService = ServiceFactorySingleton.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id != null && !id.isBlank()){
            resp.getWriter().write(mapper.writeValueAsString(genreService.get(Long.parseLong(id)).get()));
        } else {
            List<GenreDTO> data = genreService.get();

            resp.getWriter().write(mapper.writeValueAsString(data));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreDTO vote = mapper.readValue(req.getInputStream(), GenreDTO.class);

        this.genreService.create(vote);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        GenreDTO vote = mapper.readValue(req.getInputStream(), GenreDTO.class);

        this.genreService.update(Long.parseLong(id), vote);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        this.genreService.delete(Long.parseLong(id));
    }
}
