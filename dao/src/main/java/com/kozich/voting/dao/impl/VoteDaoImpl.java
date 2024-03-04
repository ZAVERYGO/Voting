package com.kozich.voting.dao.impl;

import com.kozich.voting.dao.Database;
import com.kozich.voting.dao.api.VoteDao;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

public class VoteDaoImpl implements VoteDao {

    private final Database database;

    public VoteDaoImpl() {
        this.database = Database.getInstance();
    }

    @Override
    public synchronized void save(String artist, String[] genres, String about) {
        database.getArtistMap().compute(artist, (k, v) -> v != null ? v + 1 : 1);

        for (String genre : genres) {
            database.getGenreMap().compute(genre, (k, v) -> v != null? v + 1: 1);
        }

        LocalDateTime date = LocalDateTime.now();
        database.getAboutMap().put(date.toString(), about);
    }


    @Override
    public Map<String, Integer> getArtist() {
        return Collections.unmodifiableMap(database.getArtistMap());
    }

    @Override
    public Map<String, Integer> getGenre() {
        return Collections.unmodifiableMap(database.getGenreMap());
    }

    @Override
    public Map<String, String> getAbout() {
        return Collections.unmodifiableMap(database.getAboutMap());
    }
}
