package com.kozich.voting.service.impl;

import com.kozich.voting.entity.GenreEntity;
import com.kozich.voting.dao.api.GenreDao;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.GenreService;
import com.kozich.voting.service.api.dto.GenreDTO;
import com.kozich.voting.service.converter.ConverterGenre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;
    private final ConverterGenre converter;

    public GenreServiceImpl(GenreDao dao,
                        ConverterGenre converter) {
        this.converter = converter;
        this.dao = dao;
    }

    @Override
    public GenreDTO create(GenreDTO data) {
        GenreEntity entity = new GenreEntity();
        entity.setName(data.getName());

        entity = dao.create(entity);
        return converter.convertEntityToDto(entity);
    }

    @Override
    public List<GenreDTO> get() {
        return converter.convertListEntityToListDto(dao.get());
    }

    @Override
    public Optional<GenreDTO> get(long id) {
        Optional<GenreEntity> optional = dao.get(id);
        return optional.map(converter::convertEntityToDto);
    }

    @Override
    public GenreDTO update(long id, GenreDTO data) {

        Optional<GenreEntity> optional = this.dao.get(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Жанра не существует");
        }

        GenreEntity entity = optional.get();
        entity.setName(data.getName());

        entity = dao.update(id, entity);
        return converter.convertEntityToDto(entity);
    }

    @Override
    public void delete(long id) {
        this.dao.delete(id);
    }
}
