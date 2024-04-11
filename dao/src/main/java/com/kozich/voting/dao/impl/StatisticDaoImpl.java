package com.kozich.voting.dao.impl;

import com.kozich.voting.dao.api.StatisticDao;
import com.kozich.voting.dao.api.dto.StatDTO;
import com.kozich.voting.dao.api.dto.StatType;
import com.kozich.voting.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticDaoImpl implements StatisticDao {
    private final static String SELECT_SQL = "SELECT" +
            " '" + StatType.GENRE.getName() + "' AS stat," +
            "    genre_id as id," +
            "    count(genre_id) as cnt " +
            "FROM" +
            " app.cross_vote_genre " +
            "GROUP BY" +
            " genre_id " +
            "UNION ALL " +
            "SELECT" +
            " '" + StatType.ARTIST.getName() + "' AS stat," +
            "    artist_id as id," +
            "    count(artist_id) as cnt " +
            "FROM" +
            "    app.vote " +
            "GROUP BY" +
            " artist_id;";

    @Override
    public StatDTO get(){
        StatDTO data = new StatDTO();
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_SQL);
        ){
            while (rs.next()) {
                String stat = rs.getString("stat");

                StatType.find(stat).getConsumer().accept(data, rs);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }
}
