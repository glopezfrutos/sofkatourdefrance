package com.sofka.tourdefrance.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Team {

    @Id
    private String id;

    @Indexed(unique = true)
    private String teamName;

    @Indexed(unique = true)
    private String shortName;
    private String countryId;
    private String countryName;
}
