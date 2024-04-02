package com.kozich.voting.dao.factory;

import com.kozich.voting.dao.api.ArtistDao;
import com.kozich.voting.dao.api.GenreDao;
import com.kozich.voting.dao.api.VoteDao;
import com.kozich.voting.dao.impl.ArtistDaoImpl;
import com.kozich.voting.dao.impl.GenreDaoImpl;
import com.kozich.voting.dao.impl.VoteDaoImpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private volatile static VoteDao voteDao;
    private volatile static ArtistDao artistDao;
    private volatile static GenreDao genreDao;
    private static final String url = "jdbc:postgresql://localhost:5432/voting";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "voting_app");
        props.setProperty("password", "123456");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static VoteDao getVoteDao(){
        if(voteDao == null){
            synchronized (DaoFactory.class){
                if(voteDao == null){
                    voteDao = new VoteDaoImpl();
                }
            }
        }
        return voteDao;
    }

    public static ArtistDao getArtistDao(){
        if(artistDao == null){
            synchronized (DaoFactory.class){
                if(artistDao == null){
                    artistDao = new ArtistDaoImpl();
                }
            }
        }
        return artistDao;
    }

    public static GenreDao getGenreDao(){
        if(genreDao == null){
            synchronized (DaoFactory.class){
                if(genreDao == null){
                    genreDao = new GenreDaoImpl();
                }
            }
        }
        return genreDao;
    }


    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e){
            throw new IllegalStateException("Невозможно подключиться к базе данных", e);
        }
    }
}
