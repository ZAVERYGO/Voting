package com.kozich.voting.service.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllStatDtoBuilder {
    private StatDto artistStat;
    private StatDto genreStat;
    private Map<String, String> abouts;

    private AllStatDtoBuilder() {
    }

    public static AllStatDtoBuilder builder(){
        return new AllStatDtoBuilder();
    }

    public AllStatDtoBuilder setArtistStat(StatDto artistStat) {
        this.artistStat = artistStat;
        return this;
    }

    public AllStatDtoBuilder setArtistStat(List<Map.Entry<String, Integer>> score) {
        this.artistStat = new StatDto(score);
        return this;
    }

    public AllStatDtoBuilder setGenreStat(StatDto genreStat) {
        this.genreStat = genreStat;
        return this;
    }

    public AllStatDtoBuilder setGenreStat(List<Map.Entry<String, Integer>> score) {
        this.genreStat = new StatDto(score);
        return this;
    }

    public AllStatDtoBuilder setAbouts(Map<String, String> abouts) {
        this.abouts = abouts;
        return this;
    }

    public AllStatDtoBuilder addAbouts(String about) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.abouts.put(localDateTime.toString(), about);
        return this;
    }

    public AllStatDto build(){
        return new AllStatDto(artistStat, genreStat, abouts);
    }
}
