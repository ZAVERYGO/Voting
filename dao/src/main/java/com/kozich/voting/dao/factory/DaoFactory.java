package com.kozich.voting.dao.factory;

import com.kozich.voting.dao.impl.VoteDaoImpl;

public class DaoFactory {
    private volatile static VoteDaoImpl voteDao;

    public static VoteDaoImpl getVoteDao(){
        if(voteDao == null){
            synchronized (DaoFactory.class){
                if(voteDao == null){
                    voteDao = new VoteDaoImpl();
                }
            }
        }
        return voteDao;
    }

}
