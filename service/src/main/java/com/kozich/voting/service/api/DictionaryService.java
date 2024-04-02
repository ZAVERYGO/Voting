package com.kozich.voting.service.api;

import java.util.List;
import java.util.Optional;

public interface DictionaryService<T> {
    List<T> get();
    Optional<T> get(long id);
}
