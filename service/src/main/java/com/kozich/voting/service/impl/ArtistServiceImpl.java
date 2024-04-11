package com.kozich.voting.service.impl;


import com.kozich.voting.entity.ArtistEntity;
import com.kozich.voting.dao.api.ArtistDao;
import com.kozich.voting.service.api.ArtistService;
import com.kozich.voting.service.api.dto.ArtistDTO;
import com.kozich.voting.service.converter.ConverterArtist;

import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistDao dao;
    private final ConverterArtist converter;

    public ArtistServiceImpl(ArtistDao dao,
                         ConverterArtist converter) {
        this.converter = converter;
        this.dao = dao;
    }

    @Override
    public ArtistDTO create(ArtistDTO data) {
        ArtistEntity entity = new ArtistEntity();
        entity.setName(data.getName());

        entity = dao.create(entity);
        return converter.convertEntityToDto(entity);
    }

    @Override
    public List<ArtistDTO> get() {
        dao.get();
        return converter.convertListEntityToListDto(dao.get());
    }

    @Override
    public Optional<ArtistDTO> get(long id) {
        Optional<ArtistEntity> optional = dao.get(id);

        return optional.map(converter::convertEntityToDto);
    }

    @Override
    public ArtistDTO update(long id, ArtistDTO data) {

        Optional<ArtistEntity> optional = this.dao.get(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Артиста не существует");
        }

        ArtistEntity entity = optional.get();
        entity.setName(data.getName());

        entity = dao.update(id, entity);
        return converter.convertEntityToDto(entity);
    }

    @Override
    public void delete(long id) {
        this.dao.delete(id);
    }
}