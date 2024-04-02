package com.kozich.voting.service.impl;


import com.kozich.voting.Entity.ArtistEntity;
import com.kozich.voting.dao.api.ArtistDao;
import com.kozich.voting.service.api.ArtistService;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.dto.ArtistDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistDao dao;
    private final Converter<ArtistEntity, ArtistDTO> entityToDto;
//    private final IConverter<ArtistDTO, ArtistEntity> dtoToEntity;

    public ArtistServiceImpl(ArtistDao dao,
                         Converter<ArtistEntity, ArtistDTO> entityToDto) {
        this.entityToDto = entityToDto;
        this.dao = dao;
    }

    @Override
    public List<ArtistDTO> get() {
        List<ArtistDTO> data = new ArrayList<>();
        for (ArtistEntity entity : dao.get()) {
            data.add(entityToDto.convert(entity));
        }
        return data;
    }

    @Override
    public Optional<ArtistDTO> get(long id) {
        Optional<ArtistEntity> optional = dao.get(id);

        return optional.map(entityToDto::convert);
    }
}