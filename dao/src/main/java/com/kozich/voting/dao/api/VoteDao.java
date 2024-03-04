package com.kozich.voting.dao.api;

import java.util.Map;

public interface VoteDao {

    void save(String artist, String[] genres, String about);

    Map<String, Integer> getArtist();
    Map<String, Integer> getGenre();
    Map<String, String> getAbout();
}
