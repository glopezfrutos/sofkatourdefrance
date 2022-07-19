package com.sofka.tourdefrance.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CountryDto {

    private String id;

    @NotEmpty(message = "Country must not be empty")
    @NotNull(message = "Country must not be null")
    private String countryName;
}
