package com.kozich.voting.service.api;

import com.kozich.voting.service.api.dto.AllStatDto;
import com.kozich.voting.service.api.dto.StatDto;

public interface StatService {
    AllStatDto get();


    StatDto getArtistStat();


    Integer getByArtist(String artist);


    StatDto getGenreStat();


    Integer getByGenre(String genre);


}
