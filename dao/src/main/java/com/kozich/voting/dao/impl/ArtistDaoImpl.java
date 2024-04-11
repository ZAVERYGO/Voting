package com.kozich.voting.dao.impl;

import com.kozich.voting.entity.ArtistEntity;
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

    private final static String INSERT = "INSERT INTO app.artist (name) VALUES ";
    private final static String GET_LIST = "SELECT artist_id, name FROM app.artist";
    private final static String GET_BY_ID = GET_LIST + " WHERE artist_id = ";
    private final static String UPDATE_BY_ID = "UPDATE app.artist SET name = '%s' WHERE artist_id = %d RETURNING artist_id, name";
    private final static String DELETE_BY_ID = "DELETE FROM app.artist WHERE artist_id = %d";

    @Override
    public ArtistEntity create(ArtistEntity data) {
        StringBuilder sql = new StringBuilder(INSERT);

        sql.append("('")
                .append(data.getName())
                .append("')  RETURNING artist_id, name");

        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("artist_id"));
                entity.setName(rs.getString("name"));
                return entity;
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При вставке данных произошла ошибка");
    }

    @Override
    public List<ArtistEntity> get() {
        List<ArtistEntity> data = new ArrayList<>();
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GET_LIST);
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity()
                        .setId(rs.getLong("artist_id"))
                        .setName(rs.getString("name"));
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
                    entity = new ArtistEntity()
                            .setId(rs.getLong("artist_id"))
                            .setName(rs.getString("name"));
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

    @Override
    public ArtistEntity update(long id, ArtistEntity data) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format(UPDATE_BY_ID, data.getName(), data.getId()));
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("artist_id"));
                entity.setName(rs.getString("name"));
                return entity;
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При обновлении данных произошла ошибка");
    }

    @Override
    public void delete(long id) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
        ){
            int count = st.executeUpdate(String.format(DELETE_BY_ID, id));

            if(count == 0){
                throw new IllegalArgumentException("Артиста не существует");
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

}
