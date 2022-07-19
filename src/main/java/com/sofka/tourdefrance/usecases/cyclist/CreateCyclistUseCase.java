package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.mappers.CyclistMapper;
import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.repository.ICyclistRepository;
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
public class CreateCyclistUseCase {

    private final ICyclistRepository repository;
    private final CyclistMapper mapper;

    public Mono<CyclistDto> apply(@Valid CyclistDto dto) {
        log.info("\n***** Creating cyclist: {} *****\n", dto.getFirstName() + " " + dto.getLastName());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
