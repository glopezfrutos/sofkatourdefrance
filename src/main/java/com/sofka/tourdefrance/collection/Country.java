package com.sofka.tourdefrance.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Country {

    @Id
    private String id;
    @Indexed(unique = true)
    private String countryName;
}
