package com.sofka.tourdefrance.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CyclistDto {
    private String id;

    @NotEmpty(message = "firstName may not be empty")
    private String firstName;

    @NotEmpty(message = "lastName may not be empty")
    private String lastName;

    @Min(1)
    @Max(999)
    @NotNull(message = "number may not be null")
    private Integer number;

    @NotEmpty(message = "teamId may not be empty")
    private String teamId;

    @NotEmpty(message = "teamName may not be empty")
    private String teamName;

    @NotEmpty(message = "countryId may not be empty")
    private String countryId;

    @NotEmpty(message = "countryName may not be empty")
    private String countryName;
}
