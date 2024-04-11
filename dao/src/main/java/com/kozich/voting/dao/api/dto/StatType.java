package com.kozich.voting.dao.api.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public enum StatType {
    GENRE("genre_id", ((statDTO, rs) -> {
        try {
            statDTO.addGenre(rs.getLong("id"), rs.getLong("cnt"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    })),
    ARTIST("artist_id", ((statDTO, rs) -> {
        try {
            statDTO.addArtist(rs.getLong("id"), rs.getLong("cnt"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    })),
    ;

    private String name;
    private BiConsumer<StatDTO, ResultSet> consumer;

    StatType(String name, BiConsumer<StatDTO, ResultSet> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public String getName() {
        return name;
    }

    public BiConsumer<StatDTO, ResultSet> getConsumer() {
        return consumer;
    }

    public static StatType find(String statName){
        for (StatType value : values()) {
            if(value.getName().equals(statName)){
                return value;
            }
        }

        throw new IllegalStateException("Невозможно обработать статистику");
    }
}
