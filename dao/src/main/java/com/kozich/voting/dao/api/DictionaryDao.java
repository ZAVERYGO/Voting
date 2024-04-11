package com.kozich.voting.dao.api;

import java.util.List;
import java.util.Optional;

public interface DictionaryDao<T> {

    T create(T data);
    List<T> get();
    Optional<T> get(long id);
    T update(long id, T data);
    void delete(long id);
}
