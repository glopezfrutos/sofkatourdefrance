package com.sofka.tourdefrance.mappers;


import com.sofka.tourdefrance.collection.Team;
import com.sofka.tourdefrance.model.TeamDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TeamMapper {

    private final ModelMapper modelMapper;

    public Function<Team, TeamDto> convertEntityToDto() {
        return entity -> modelMapper.map(entity, TeamDto.class);
    }

    public Function<TeamDto, Team> convertDtoToEntity() {
        return dto -> modelMapper.map(dto, Team.class);
    }
}
