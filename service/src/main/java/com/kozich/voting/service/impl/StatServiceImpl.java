package com.kozich.voting.service.impl;

import com.kozich.voting.dao.api.StatisticDao;
import com.kozich.voting.dao.api.dto.StatDTO;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.api.VoteService;

public class StatServiceImpl implements StatService {

    private final StatisticDao dao;
    private final VoteService voteService;

    public StatServiceImpl(StatisticDao dao, VoteService voteService) {
        this.dao = dao;
        this.voteService = voteService;
    }

    @Override
    public StatDTO get() {
        return dao.get();
    }
}
