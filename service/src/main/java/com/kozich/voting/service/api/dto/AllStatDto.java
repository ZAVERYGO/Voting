package com.kozich.voting.service.api.dto;

import java.util.Map;

public class AllStatDto {
    private final StatDto artistStat;
    private final StatDto genreStat;
    private final Map<String, String> abouts;

    public AllStatDto(StatDto artistStat, StatDto genreStat, Map<String, String> abouts) {
        this.artistStat = artistStat;
        this.genreStat = genreStat;
        this.abouts = abouts;
    }

    public StatDto getArtistStat() {
        return artistStat;
    }

    public StatDto getGenreStat() {
        return genreStat;
    }

    public Map<String, String> getAbouts() {
        return abouts;
    }
}
