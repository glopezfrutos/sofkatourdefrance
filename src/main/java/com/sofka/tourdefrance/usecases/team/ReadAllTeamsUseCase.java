package com.sofka.tourdefrance.usecases.team;

import com.sofka.tourdefrance.mappers.TeamMapper;
import com.sofka.tourdefrance.model.TeamDto;
import com.sofka.tourdefrance.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadAllTeamsUseCase implements Supplier<Flux<TeamDto>> {

    private final ITeamRepository repository;
    private final TeamMapper mapper;

    @Override
    public Flux<TeamDto> get() {
        log.info("\n***** Reading all teams *****\n");
        return repository
                .findAll()
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

