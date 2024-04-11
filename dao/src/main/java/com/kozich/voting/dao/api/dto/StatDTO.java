package com.kozich.voting.dao.api.dto;

import java.util.HashMap;
import java.util.Map;

public class StatDTO {
    private Map<Long, Long> genres = new HashMap<>();
    private Map<Long, Long> artist = new HashMap<>();

    public Map<Long, Long> getGenres() {
        return genres;
    }

    public Map<Long, Long> getArtist() {
        return artist;
    }

    public void addGenre(Long genre, Long count){
        this.genres.put(genre, count);
    }
    public void addArtist(Long artist, Long count){
        this.artist.put(artist, count);
    }
}
