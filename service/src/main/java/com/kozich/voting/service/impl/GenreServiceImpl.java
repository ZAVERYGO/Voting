package com.kozich.voting.service.impl;

import com.kozich.voting.Entity.GenreEntity;
import com.kozich.voting.dao.api.GenreDao;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.GenreService;
import com.kozich.voting.service.api.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;
    private final Converter<GenreEntity, GenreDTO> entityToDto;
//    private final IConverter<GenreDTO, GenreEntity> dtoToEntity;

    public GenreServiceImpl(GenreDao dao,
                        Converter<GenreEntity, GenreDTO> entityToDto) {
        this.entityToDto = entityToDto;
        this.dao = dao;
    }

    @Override
    public List<GenreDTO> get() {
        List<GenreDTO> data = new ArrayList<>();
        for (GenreEntity entity : dao.get()) {
            data.add(entityToDto.convert(entity));
        }
        return data;
    }

    @Override
    public Optional<GenreDTO> get(long id) {
        Optional<GenreEntity> optional = dao.get(id);

        return optional.map(entityToDto::convert);
    }
}
