package com.kozich.voting.entity;


import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArtistEntity {

    private long id;

    private String name;

}
