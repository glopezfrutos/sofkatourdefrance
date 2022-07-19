package com.sofka.tourdefrance.mappers;

import com.sofka.tourdefrance.collection.Cyclist;
import com.sofka.tourdefrance.model.CyclistDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CyclistMapper {

    private final ModelMapper modelMapper;

    public Function<Cyclist, CyclistDto> convertEntityToDto() {
        return entity -> modelMapper.map(entity, CyclistDto.class);
    }

    public Function<CyclistDto, Cyclist> convertDtoToEntity() {
        return dto -> modelMapper.map(dto, Cyclist.class);
    }
}
