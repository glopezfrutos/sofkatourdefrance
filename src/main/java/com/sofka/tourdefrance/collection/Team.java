package com.sofka.tourdefrance.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Team {

    @Id
    private String id;
    private String teamName;
    private String shortName;
    private String countryId;
    private String countryName;
}
