package com.kozich.voting.service.factory;

import com.kozich.voting.dao.factory.DaoFactory;
import com.kozich.voting.service.api.ArtistService;
import com.kozich.voting.service.api.GenreService;
import com.kozich.voting.service.api.StatService;
import com.kozich.voting.service.api.VoteService;
import com.kozich.voting.service.converter.ConverterArtist;
import com.kozich.voting.service.converter.ConverterGenre;
import com.kozich.voting.service.converter.ConverterVote;
import com.kozich.voting.service.impl.ArtistServiceImpl;
import com.kozich.voting.service.impl.GenreServiceImpl;
import com.kozich.voting.service.impl.StatServiceImpl;
import com.kozich.voting.service.impl.VoteServiceImpl;

import static com.kozich.voting.dao.factory.DaoFactory.getGenreDao;

public class ServiceFactorySingleton {

    private volatile static StatService statService;
    private volatile static VoteService voteService;
    private volatile static ArtistService artistService;
    private volatile static GenreService genreService;

   /* public static StatService getStatService(){
        if(statService == null){
            synchronized (ServiceFactorySingleton.class){
                if(statService == null){
                    statService = new StatServiceImpl(getVoteService());
                }
            }
        }
        return statService;
    }*/


    public static StatService getStatService(){
        if(statService == null){
            synchronized (ServiceFactorySingleton.class){
                if(statService == null){
                    statService = new StatServiceImpl(DaoFactory.getStatisticDao(), getVoteService());
                }
            }
        }
        return statService;
    }


    public static ArtistService getArtistService(){
        if(artistService == null){
            synchronized (ServiceFactorySingleton.class){
                if(artistService == null){
                    artistService = new ArtistServiceImpl(DaoFactory.getArtistDao(), new ConverterArtist());
                }
            }
        }
        return artistService;
    }


    public static GenreService getGenreService(){
        if(genreService == null){
            synchronized (ServiceFactorySingleton.class){
                if(genreService == null){
                    genreService = new GenreServiceImpl(getGenreDao(), new ConverterGenre());
                }
            }
        }
        return genreService;
    }
    public static VoteService getVoteService(){
        if(voteService == null){
            synchronized (ServiceFactorySingleton.class){
                if(voteService == null){
                    voteService = new VoteServiceImpl(DaoFactory.getVoteDao(), getGenreService(), getArtistService());
                }
            }
        }
        return voteService;
    }
}
