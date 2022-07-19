package com.sofka.tourdefrance.usecases.country;

import com.sofka.tourdefrance.mappers.CountryMapper;
import com.sofka.tourdefrance.model.CountryDto;
import com.sofka.tourdefrance.repository.ICountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadAllCountriesUseCase implements Supplier<Flux<CountryDto>> {

    private final ICountryRepository repository;
    private final CountryMapper mapper;

    @Override
    public Flux<CountryDto> get() {
        log.info("\n***** Reading all countries *****\n");
        return repository
                .findAll()
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}

