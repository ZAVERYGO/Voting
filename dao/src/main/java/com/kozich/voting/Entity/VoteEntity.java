package com.kozich.voting.Entity;


import java.time.LocalDateTime;

public class VoteEntity {
    private long id;
    private LocalDateTime dt_create;
    private long artistId;

    private String about;

    public VoteEntity() {
    }

    public VoteEntity(long id, long artistId, String about) {
        this.id = id;
        this.artistId = artistId;
        this.about = about;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artist_id) {
        this.artistId = artist_id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
