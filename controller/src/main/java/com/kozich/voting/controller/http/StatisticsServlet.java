package com.kozich.voting.controller.http;



import com.kozich.voting.controller.http.utils.ServletUtils;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.api.dto.AllStatDto;
import com.kozich.voting.service.factory.ServiceFactorySingleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class StatisticsServlet extends HttpServlet {

    private final StatService statService = ServiceFactorySingleton.getStatService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        AllStatDto allStatDto = statService.get();

        ServletUtils.writeTo(allStatDto.getArtistStat().getScore(), writer);
        ServletUtils.writeBrakeLine(writer);
        ServletUtils.writeTo(allStatDto.getGenreStat().getScore(), writer);
        ServletUtils.writeBrakeLine(writer);
        ServletUtils.writeTo(allStatDto.getAbouts(), writer);
    }
}
