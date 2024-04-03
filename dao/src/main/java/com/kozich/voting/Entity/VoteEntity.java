package com.kozich.voting.Entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Setter
@Getter
public class VoteEntity {
    private long id;
    private LocalDateTime dt_create;
    private String about;
    private long artistId;
    private long[] genresId;

    public VoteEntity() {
    }

    public VoteEntity(long id, LocalDateTime dt_create, String about, long artistId, long[] genresId) {
        this.id = id;
        this.dt_create = dt_create;
        this.about = about;
        this.artistId = artistId;
        this.genresId = genresId;
    }


}
