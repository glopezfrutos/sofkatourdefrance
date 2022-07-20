package com.sofka.tourdefrance.usecases.team;

import com.sofka.tourdefrance.mappers.TeamMapper;
import com.sofka.tourdefrance.model.TeamDto;
import com.sofka.tourdefrance.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class CreateTeamUseCase {

    private final ITeamRepository repository;
    private final TeamMapper mapper;

    public Mono<TeamDto> apply(@Valid TeamDto dto) {
        log.info("\n***** Creating team: {} *****\n", dto.getTeamName());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
