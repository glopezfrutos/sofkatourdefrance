package com.sofka.tourdefrance.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Cyclist {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private Integer number;
    private String teamId;
    private String teamName;
    private String countryId;
    private String countryName;
}
