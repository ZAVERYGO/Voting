package com.kozich.voting.dao.api;

import com.kozich.voting.entity.VoteEntity;


public interface VoteDao {

    void save(VoteEntity voteEntity);

}
