package com.kozich.voting.service.converter;

import com.kozich.voting.entity.ArtistEntity;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.dto.ArtistDTO;

import java.util.List;

public class ConverterArtist implements Converter<ArtistEntity, ArtistDTO> {


    @Override
    public ArtistEntity convertDtoToEntity(ArtistDTO artistDTO) {
        return new ArtistEntity()
                .setId(artistDTO.getId())
                .setName(artistDTO.getName());
    }

    @Override
    public ArtistDTO convertEntityToDto(ArtistEntity artistEntity) {
        return new ArtistDTO()
                .setId(artistEntity.getId())
                .setName(artistEntity.getName());
    }

    @Override
    public List<ArtistEntity> convertListDtoToListEntity(List<ArtistDTO> listDto) {
        return null;
    }

    @Override
    public List<ArtistDTO> convertListEntityToListDto(List<ArtistEntity> listEntity) {
        return null;
    }
}
