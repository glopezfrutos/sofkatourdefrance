package com.sofka.tourdefrance.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TeamDto {
    private String id;

    @NotEmpty(message = "teamName may not be empty")
    private String teamName;

    @NotEmpty(message = "shortName may not be empty")
    private String shortName;

    @NotEmpty(message = "countryId may not be empty")
    private String countryId;

    @NotEmpty(message = "countryName may not be empty")
    private String countryName;
}
