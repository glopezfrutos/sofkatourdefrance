package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.mappers.CyclistMapper;
import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.repository.ICyclistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadCyclistByTeamIdUseCase {

    private final ICyclistRepository repository;
    private final CyclistMapper mapper;

    public Flux<CyclistDto> get(@NotEmpty String teamId) {
        log.info("\n***** Reading cyclists by teamId {} *****\n", teamId);
        return repository
                .findAll()
                .filter(cyclist -> Objects.equals(cyclist.getTeamId(), teamId))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

