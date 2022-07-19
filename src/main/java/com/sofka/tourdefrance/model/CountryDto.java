package com.sofka.tourdefrance.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CountryDto {

    private String id;

    @NotEmpty(message = "Country may not be empty")
    private String countryName;
}
