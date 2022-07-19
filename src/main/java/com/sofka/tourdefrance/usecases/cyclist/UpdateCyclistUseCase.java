package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.mappers.CyclistMapper;
import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.repository.ICyclistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCyclistUseCase implements Function<CyclistDto, Mono<CyclistDto>> {

    private final ICyclistRepository repository;
    private final CyclistMapper mapper;


    @Override
    public Mono<CyclistDto> apply(CyclistDto dto) {
        log.info("\n***** Updating cyclist by Id: {} *****\n", dto.getId());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
