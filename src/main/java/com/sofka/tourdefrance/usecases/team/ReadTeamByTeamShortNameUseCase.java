package com.sofka.tourdefrance.usecases.team;

import com.sofka.tourdefrance.mappers.TeamMapper;
import com.sofka.tourdefrance.model.TeamDto;
import com.sofka.tourdefrance.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadTeamByTeamShortNameUseCase {

    private final ITeamRepository repository;
    private final TeamMapper mapper;

    public Mono<TeamDto> get(@NotEmpty String shortName) {
        log.info("\n***** Reading team by shortName: {} *****\n", shortName);
        return repository
                .findAll()
                .filter(team -> Objects.equals(team.getShortName(), shortName))
                .next()
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

