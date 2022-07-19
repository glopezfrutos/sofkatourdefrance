package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.mappers.CyclistMapper;
import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.repository.ICyclistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadCyclistByNumberUseCase {

    private final ICyclistRepository repository;
    private final CyclistMapper mapper;

    public Mono<CyclistDto> get(@NotEmpty Integer number) {
        log.info("\n***** Reading cyclist by number {} *****\n", number);
        return repository
                .findAll()
                .filter(cyclist -> Objects.equals(cyclist.getNumber(), number))
                .next()
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

