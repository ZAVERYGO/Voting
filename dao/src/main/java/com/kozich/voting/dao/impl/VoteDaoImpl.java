package com.kozich.voting.dao.impl;

import com.kozich.voting.entity.VoteEntity;

import com.kozich.voting.dao.api.VoteDao;
import com.kozich.voting.dao.factory.DaoFactory;

import java.sql.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class VoteDaoImpl implements VoteDao {

    private final static String GET_VOTES = "SELECT vote_id, dt_create, artist, about FROM app.vote";

    private final static String INSERT_VOTE = "INSERT INTO app.vote(dt_create, artist_id, about) VALUES ";

    /*private final static String GET_GENRES_FROM_CROSS_BY_VOTES_ID = "SELECT genre_id FROM app.cross_vote_genre" +
                                                                            " WHERE vote_id = ";
    private final static String GET_VOTES_BY_ID = GET_VOTES + " WHERE genre_id = ";*/

    private final static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();

    /*@Override
    public List<VoteEntity> get() {
        List<VoteEntity> data = new ArrayList<>();
        try(Connection conn = DaoFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSetVotes = statement.executeQuery(GET_VOTES);
        ){
            List<Long> genresId  = new ArrayList<>();
            while (resultSetVotes.next()) {
                try(ResultSet resultSetGenres = statement.executeQuery(GET_GENRES_FROM_CROSS_BY_VOTES_ID +
                        resultSetVotes.getLong("genre_id"))) {
                    while (resultSetGenres.next()) {
                        genresId.add(resultSetGenres.getLong("genre_id"));
                    }
                }
                VoteEntity entity = new VoteEntity()
                        .setId(resultSetVotes.getLong("vote_id"))
                        .setDtCreate(resultSetVotes.getTimestamp("dt_create").toLocalDateTime())
                        .setGenresId(genresId.stream().mapToLong(Long::longValue).toArray())
                        .setArtistId(resultSetVotes.getLong("artist_id"))
                        .setAbout(resultSetVotes.getString("about"));
                data.add(entity);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }
    @Override
    public Optional<VoteEntity> get(long id) {
        List<Long> genresId;
        try(Connection conn = DaoFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSetVotes = statement.executeQuery(GET_VOTES_BY_ID + id);
        ){
            VoteEntity entity = null;
            while (resultSetVotes.next()) {
                try(ResultSet resultSetGenres = statement.executeQuery(GET_GENRES_FROM_CROSS_BY_VOTES_ID +
                                                                        resultSetVotes.getLong("genre_id"))) {
                    genresId = new ArrayList<>();
                    while (resultSetGenres.next()) {
                        genresId.add(resultSetGenres.getLong("genre_id"));
                    }
                }
                if(entity == null){
                    entity = new VoteEntity()
                            .setId(resultSetVotes.getLong("vote_id"))
                            .setDtCreate(resultSetVotes.getTimestamp("dt_create").toLocalDateTime())
                            .setGenresId((genresId.stream().mapToLong(Long::longValue).toArray()))
                            .setArtistId(resultSetVotes.getLong("artist_id"))
                            .setAbout(resultSetVotes.getString("about"));
                } else {
                    throw new IllegalStateException("В запросе получилось больше одного значения");
                }
            }

            if(entity == null){
                return Optional.empty();
            } else {
                return Optional.of(entity);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }*/

    @Override
    public void save(VoteEntity entity) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
        ){
            String sql = INSERT_VOTE + "('" +
                    entity.getDtCreate().format(formatter) + "', " + entity.getArtistId() + ", '" + entity.getAbout() + "') RETURNING vote_id;";

            ResultSet resultSet = st.executeQuery(sql);

            resultSet.next();
            Long voteId = resultSet.getLong("vote_id");

            StringBuilder builderCrossInsert = new StringBuilder("INSERT INTO app.cross_vote_genre(vote_id, genre_id) VALUES ");

            long[] genres = entity.getGenresId();

            boolean needComma = false;
            for (long genre : genres) {
                if(needComma){
                    builderCrossInsert.append(", ");
                } else {
                    needComma = true;
                }
                builderCrossInsert.append("(")
                        .append(voteId).append(",")
                        .append(genre)
                        .append(")");
            }

            st.executeUpdate(builderCrossInsert.toString());
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }
}
;