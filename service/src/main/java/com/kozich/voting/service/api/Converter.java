package com.kozich.voting.service.api;

import java.util.List;

public interface Converter<E, D> {
    E convertDtoToEntity(D item);

    D convertEntityToDto(E item);

    List<E> convertListDtoToListEntity(List<D> listDto);

    List<D> convertListEntityToListDto(List<E> listEntity);

}