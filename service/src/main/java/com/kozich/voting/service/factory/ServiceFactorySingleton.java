package com.kozich.voting.service.factory;

import com.kozich.voting.dao.factory.DaoFactory;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.impl.StatServiceImpl;
import com.kozich.voting.service.impl.VoteServiceImpl;

public class ServiceFactorySingleton {

    private volatile static StatService statService;
    private volatile static VoteServiceImpl voteService;

    public static StatService getStatService(){
        if(statService == null){
            synchronized (ServiceFactorySingleton.class){
                if(statService == null){
                    statService = new StatServiceImpl(getVoteService());
                }
            }
        }
        return statService;
    }

    public static VoteServiceImpl getVoteService(){
        if(voteService == null){
            synchronized (ServiceFactorySingleton.class){
                if(voteService == null){
                    voteService = new VoteServiceImpl(DaoFactory.getVoteDao());
                }
            }
        }
        return voteService;
    }
}
