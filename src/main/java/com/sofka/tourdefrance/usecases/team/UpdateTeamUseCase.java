package com.sofka.tourdefrance.usecases.team;

import com.sofka.tourdefrance.mappers.TeamMapper;
import com.sofka.tourdefrance.model.TeamDto;
import com.sofka.tourdefrance.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateTeamUseCase implements Function<TeamDto, Mono<TeamDto>> {

    private final ITeamRepository repository;
    private final TeamMapper mapper;


    @Override
    public Mono<TeamDto> apply(TeamDto dto) {
        log.info("\n***** Updating team by Id: {} *****\n", dto.getId());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
