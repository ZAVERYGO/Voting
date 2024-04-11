package com.kozich.voting.controller.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kozich.voting.controller.factory.ControllerFactory;
import com.kozich.voting.service.api.ArtistService;
import com.kozich.voting.service.api.dto.ArtistDTO;
import com.kozich.voting.service.factory.ServiceFactorySingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ArtistServlet extends HttpServlet {

    private final ObjectMapper mapper = ControllerFactory.getMapper();
    private final ArtistService artistService = ServiceFactorySingleton.getArtistService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null && !id.isBlank()){
            resp.getWriter().write(mapper.writeValueAsString(artistService.get(Long.parseLong(id)).get()));
        }else{
            List<ArtistDTO> artists = artistService.get();
            resp.getWriter().write(mapper.writeValueAsString(artists));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArtistDTO vote = mapper.readValue(req.getInputStream(), ArtistDTO.class);

        this.artistService.create(vote);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ArtistDTO vote = mapper.readValue(req.getInputStream(), ArtistDTO.class);

        this.artistService.update(Long.parseLong(id), vote);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        this.artistService.delete(Long.parseLong(id));
    }
}
