package com.sofka.tourdefrance.usecases.country;

import com.sofka.tourdefrance.mappers.CountryMapper;
import com.sofka.tourdefrance.model.CountryDto;
import com.sofka.tourdefrance.repository.ICountryRepository;
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
public class CreateCountryUseCase {

    private final ICountryRepository repository;
    private final CountryMapper mapper;

    public Mono<CountryDto> apply(@Valid CountryDto dto) {
        log.info("\n***** Creating country: {} *****\n", dto.getCountryName());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
