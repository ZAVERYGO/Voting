package com.kozich.voting.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VoteEntity {

    private long id;
    private LocalDateTime dtCreate;
    private long artistId;
    private long[] genresId;
    private String about;

}
