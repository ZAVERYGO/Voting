package com.kozich.voting.service.api;

import java.util.Map;

public interface VoteService {
    void save(String artist, String[] genres, String about);

    Map<String, Integer> getArtist();
    Map<String, Integer> getGenre();
    Map<String, String> getAbout();
}
