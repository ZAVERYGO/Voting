package com.kozich.voting.controller.http;


import com.kozich.voting.controller.http.utils.ServletUtils;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.api.VoteService;
import com.kozich.voting.service.api.dto.AllStatDto;
import com.kozich.voting.service.factory.ServiceFactorySingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class VotingServlet extends HttpServlet {

    private final static String ARTIST_PARAM_NAME = "artist";
    private final static String GENRE_PARAM_NAME = "genre";
    private final static String ABOUT_PARAM_NAME = "about";

    private final VoteService voteService = ServiceFactorySingleton.getVoteService();
    private final StatService statService = ServiceFactorySingleton.getStatService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        String artist = req.getParameter(ARTIST_PARAM_NAME);
        String[] genres = req.getParameterValues(GENRE_PARAM_NAME);
        String about = req.getParameter(ABOUT_PARAM_NAME);

        this.voteService.save(artist, genres, about);

        AllStatDto allStatDto = statService.get();

        ServletUtils.writeTo(allStatDto.getArtistStat().getScore(), writer);
        ServletUtils.writeBrakeLine(writer);
        ServletUtils.writeTo(allStatDto.getGenreStat().getScore(), writer);
        ServletUtils.writeBrakeLine(writer);
        ServletUtils.writeTo(allStatDto.getAbouts(), writer);
    }
}
