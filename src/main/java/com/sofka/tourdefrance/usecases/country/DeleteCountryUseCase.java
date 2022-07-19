package com.sofka.tourdefrance.usecases.country;

import com.sofka.tourdefrance.repository.ICountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCountryUseCase {
    private final ICountryRepository repository;

    public Mono<Void> apply(String id) {
        log.info("\n***** Deleting country by Id: {} *****\n", id);
        return repository.deleteById(id);
    }
}
