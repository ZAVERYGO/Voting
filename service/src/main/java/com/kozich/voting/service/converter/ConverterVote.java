package com.kozich.voting.service.converter;

import com.kozich.voting.entity.VoteEntity;
import com.kozich.voting.service.api.Converter;
import com.kozich.voting.service.api.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class ConverterVote implements Converter<VoteEntity, VoteDTO> {
    @Override
    public VoteEntity convertDtoToEntity(VoteDTO voteDTO) {
        return new VoteEntity()
                .setArtistId(voteDTO.getArtistId())
                .setGenresId(voteDTO.getGenresId())
                .setAbout(voteDTO.getAbout());
    }

    @Override
    public VoteDTO convertEntityToDto(VoteEntity voteEntity) {
        return new VoteDTO()
                .setArtistId(voteEntity.getArtistId())
                .setGenresId(voteEntity.getGenresId())
                .setAbout(voteEntity.getAbout());
    }

    @Override
    public List<VoteEntity> convertListDtoToListEntity(List<VoteDTO> listDto) {
        List<VoteEntity> voteEntities = new ArrayList<>();
        for (VoteDTO voteDTO : listDto) {
            VoteEntity voteEntity = new VoteEntity()
                    .setArtistId(voteDTO.getArtistId())
                    .setGenresId(voteDTO.getGenresId())
                    .setAbout(voteDTO.getAbout());
            voteEntities.add(voteEntity);
        }
        return voteEntities;
    }

    @Override
    public List<VoteDTO> convertListEntityToListDto(List<VoteEntity> listEntity) {
        List<VoteDTO> voteDTOS = new ArrayList<>();
        for (VoteEntity voteEntity : listEntity) {
            VoteDTO voteDTO = new VoteDTO()
                    .setArtistId(voteEntity.getArtistId())
                    .setGenresId(voteEntity.getGenresId())
                    .setAbout(voteEntity.getAbout());
            voteDTOS.add(voteDTO);
        }
        return voteDTOS;
    }
}
