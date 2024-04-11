package com.kozich.voting.service.api;

import java.util.List;
import java.util.Optional;

public interface DictionaryService<T> {
    T create(T data);
    List<T> get();
    Optional<T> get(long id);
    T update(long id, T data);
    void delete(long id);
}
