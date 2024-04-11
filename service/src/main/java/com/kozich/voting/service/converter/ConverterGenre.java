package com.kozich.voting.service.converter;

import com.kozich.voting.entity.GenreEntity;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;

public class ConverterGenre implements Converter<GenreEntity, GenreDTO> {
    @Override
    public GenreEntity convertDtoToEntity(GenreDTO genreDTO) {
        return new GenreEntity()
                .setId(genreDTO.getId())
                .setName(genreDTO.getName());
    }

    @Override
    public GenreDTO convertEntityToDto(GenreEntity genreEntity) {
        return new GenreDTO()
                .setId(genreEntity.getId())
                .setName(genreEntity.getName());
    }

    @Override
    public List<GenreEntity> convertListDtoToListEntity(List<GenreDTO> listDto) {
        return null;
    }

    @Override
    public List<GenreDTO> convertListEntityToListDto(List<GenreEntity> listEntity) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (GenreEntity genreEntity : listEntity) {
            genreDTOList.add(convertEntityToDto(genreEntity));
        }
        return genreDTOList;
    }
}
