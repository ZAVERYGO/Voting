package com.kozich.voting.service.impl;

import com.kozich.voting.dao.api.VoteDao;
import com.kozich.voting.entity.VoteEntity;
import com.kozich.voting.service.api.ArtistService;
import com.kozich.voting.service.api.GenreService;
import com.kozich.voting.service.api.VoteService;
import com.kozich.voting.service.api.dto.ArtistDTO;
import com.kozich.voting.service.api.dto.GenreDTO;
import com.kozich.voting.service.api.dto.VoteDTO;
import com.kozich.voting.service.converter.ConverterVote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class VoteServiceImpl implements VoteService {

    private final VoteDao voteDao;
    private final GenreService genreService;
    private final ArtistService artistService;

    public VoteServiceImpl(VoteDao voteDao, GenreService genreService, ArtistService artistService) {
        this.voteDao = voteDao;
        this.genreService = genreService;
        this.artistService = artistService;
    }
    @Override
    public void save(VoteDTO vote) {
        long[] genres = vote.getGenresId();

        if(genres == null || genres.length < 3 || genres.length > 5){
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }

        for (Long genreId : genres) {
            Optional<GenreDTO> genreDTO = this.genreService.get(genreId);
            if(genreDTO.isEmpty()){
                throw new IllegalArgumentException("Выбран не существующий жанр");
            }
        }

        if(vote.getArtistId() == 0){
            throw new IllegalArgumentException("Необходимо выбрать артиста");
        }

        Optional<ArtistDTO> artistDTO = this.artistService.get(vote.getArtistId());

        if(artistDTO.isEmpty()){
            throw new IllegalArgumentException("Выбран не существующий артист");
        }

        VoteEntity entity = new VoteEntity();
        entity.setDtCreate(LocalDateTime.now());
        entity.setArtistId(vote.getArtistId());

        long[] genresId = new long[genres.length];

        for (int i = 0; i < genres.length; i++) {
            genresId[i] = genres[i];
        }

        entity.setGenresId(genresId);
        entity.setAbout(vote.getAbout());

        this.voteDao.save(entity);
    }


    /*@Override
    public List<VoteDTO> get() {
        return converterVote.convertListEntityToListDto(voteDao.get());
    }

    @Override
    public Optional<VoteDTO> get(long id) {

        Optional<VoteDTO> voteDTO = Optional.of(converterVote.convertEntityToDto(voteDao.get(id).get()));
        return voteDTO;
    }*/

}
