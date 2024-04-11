package com.kozich.voting.service.api;

import com.kozich.voting.entity.VoteEntity;
import com.kozich.voting.service.api.dto.VoteDTO;

import java.util.Map;

public interface VoteService {
    void save(VoteDTO vote);
}
