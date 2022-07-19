package com.sofka.tourdefrance.usecases.country;

import com.sofka.tourdefrance.mappers.CountryMapper;
import com.sofka.tourdefrance.model.CountryDto;
import com.sofka.tourdefrance.repository.ICountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCountryUseCase implements Function<CountryDto, Mono<CountryDto>> {

    private final ICountryRepository repository;
    private final CountryMapper mapper;


    @Override
    public Mono<CountryDto> apply(CountryDto dto) {
        log.info("\n***** Updating country to {} by Id: {} *****\n", dto.getCountryName(), dto.getId());
        return repository
                .save(mapper
                        .convertDtoToEntity()
                        .apply(dto))
                .map(entity -> mapper.convertEntityToDto().apply(entity));
    }
}
