package com.sofka.tourdefrance.mappers;

import com.sofka.tourdefrance.collection.Country;
import com.sofka.tourdefrance.model.CountryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CountryMapper {

    private final ModelMapper modelMapper;

    public Function<Country, CountryDto> convertEntityToDto() {
        return entity -> modelMapper.map(entity, CountryDto.class);
    }

    public Function<CountryDto, Country> convertDtoToEntity() {
        return dto -> modelMapper.map(dto, Country.class);
    }
}
