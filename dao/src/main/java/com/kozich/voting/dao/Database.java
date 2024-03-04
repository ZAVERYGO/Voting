package com.kozich.voting.dao;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, Integer> artistMap = new HashMap<>();
    private final Map<String, Integer> genreMap = new HashMap<>();
    private final Map<String, String> aboutMap = new HashMap<>();

    private static Database instance;

    private Database() {
    }

    public Map<String, Integer> getArtistMap() {
        return artistMap;
    }

    public Map<String, Integer> getGenreMap() {
        return genreMap;
    }

    public Map<String, String> getAboutMap() {
        return aboutMap;
    }

    public static synchronized Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
}
