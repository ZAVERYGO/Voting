package com.kozich.voting.service.impl;

import com.kozich.voting.dao.impl.VoteDaoImpl;
import com.kozich.voting.service.api.VoteService;

import java.util.Map;

public class VoteServiceImpl implements VoteService {

    private final VoteDaoImpl voteDao;

    public VoteServiceImpl(VoteDaoImpl voteDao) {
        this.voteDao = voteDao;
    }

    @Override
    public void save(String artist, String[] genres, String about) {
        if(genres == null || genres.length < 3 || genres.length > 5){
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }
        this.voteDao.save(artist, genres, about);
    }

    @Override
    public Map<String, Integer> getArtist() {
        return this.voteDao.getArtist();
    }

    @Override
    public Map<String, Integer> getGenre() {
        return this.voteDao.getGenre();
    }

    @Override
    public Map<String, String> getAbout() {
        return this.voteDao.getAbout();
    }
}
