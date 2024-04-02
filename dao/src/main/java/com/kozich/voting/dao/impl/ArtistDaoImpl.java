package com.kozich.voting.dao.impl;

import com.kozich.voting.Entity.ArtistEntity;
import com.kozich.voting.dao.api.ArtistDao;
import com.kozich.voting.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl implements ArtistDao {

    private final static String GET_LIST = "SELECT id, name FROM app.artist";
    private final static String GET_BY_ID = GET_LIST + " WHERE id = ";

    @Override
    public List<ArtistEntity> get() {
        List<ArtistEntity> data = new ArrayList<>();
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GET_LIST);
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                data.add(entity);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }

    @Override
    public Optional<ArtistEntity> get(long id) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GET_BY_ID + id);
        ){
            ArtistEntity entity = null;
            while (rs.next()) {
                if(entity == null){
                    entity = new ArtistEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setName(rs.getString("name"));
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
    }
}
