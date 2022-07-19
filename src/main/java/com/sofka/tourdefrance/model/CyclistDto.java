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

    @NotEmpty(message = "First name must not be empty")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    private String lastName;

    @Min(1)
    @Max(999)
    @NotNull
    private Integer number;

    @NotEmpty
    private String teamId;

    @NotEmpty
    private String teamName;

    @NotEmpty
    private String countryId;

    @NotEmpty
    private String countryName;
}
