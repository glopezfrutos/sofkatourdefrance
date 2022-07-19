package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.mappers.CyclistMapper;
import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.repository.ICyclistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadAllCyclistUseCase implements Supplier<Flux<CyclistDto>> {

    private final ICyclistRepository repository;
    private final CyclistMapper mapper;

    @Override
    public Flux<CyclistDto> get() {
        log.info("\n***** Reading all cyclists *****\n");
        return repository
                .findAll()
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

