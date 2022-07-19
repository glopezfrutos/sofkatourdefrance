package com.sofka.tourdefrance.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Country {

    @Id
    private String id;
    private String countryName;
}
