package com.kozich.voting.dao.impl;

import com.kozich.voting.entity.GenreEntity;
import com.kozich.voting.dao.api.GenreDao;
import com.kozich.voting.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {
    private final static String INSERT = "INSERT INTO app.genre (name) VALUES ";
    private final static String GET_LIST = "SELECT genre_id, name FROM app.genre";
    private final static String GET_BY_ID = GET_LIST + " WHERE genre_id = ";
    private final static String UPDATE_BY_ID = "UPDATE app.genre SET name = '%s' WHERE genre_id = %d RETURNING genre_id, name";
    private final static String DELETE_BY_ID = "DELETE FROM app.genre WHERE genre_id = %d";


    @Override
    public GenreEntity create(GenreEntity data) {
        StringBuilder sql = new StringBuilder(INSERT);

        sql.append("('")
                .append(data.getName())
                .append("')  RETURNING genre_id, name");

        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
        ){
            while (rs.next()) {
                GenreEntity entity = new GenreEntity();
                entity.setId(rs.getLong("genre_id"));
                entity.setName(rs.getString("name"));
                return entity;
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При вставке данных произошла ошибка");
    }

    @Override
    public List<GenreEntity> get() {
        List<GenreEntity> data = new ArrayList<>();
        try (Connection conn = DaoFactory.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(GET_LIST);) {
            while (rs.next()) {
                GenreEntity entity = new GenreEntity().setId(rs.getLong("genre_id")).setName(rs.getString("name"));
                data.add(entity);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }

    @Override
    public Optional<GenreEntity> get(long id) {
        try (Connection conn = DaoFactory.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(GET_BY_ID + id);) {
            GenreEntity entity = null;
            while (rs.next()) {
                if (entity == null) {
                    entity = new GenreEntity().setId(rs.getLong("genre_id")).setName(rs.getString("name"));
                } else {
                    throw new IllegalStateException("В запросе получилось больше одного значения");
                }
            }

            if (entity == null) {
                return Optional.empty();
            } else {
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

    @Override
    public GenreEntity update(long id, GenreEntity data) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format(UPDATE_BY_ID, data.getName(), data.getId()));
        ){
            while (rs.next()) {
                GenreEntity entity = new GenreEntity();
                entity.setId(rs.getLong("genre_id"));
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
                throw new IllegalArgumentException("Жанра не существует");
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

}
