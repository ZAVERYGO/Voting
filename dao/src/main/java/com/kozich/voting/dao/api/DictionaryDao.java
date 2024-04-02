package com.kozich.voting.dao.api;

import java.util.List;
import java.util.Optional;

public interface DictionaryDao<T> {
    List<T> get();
    Optional<T> get(long id);
}
