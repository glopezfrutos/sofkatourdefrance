package com.sofka.tourdefrance.usecases.cyclist;

import com.sofka.tourdefrance.repository.ICyclistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCyclistUseCase {
    private final ICyclistRepository repository;

    public Mono<Void> apply(String id) {
        log.info("\n***** Deleting cyclist by Id: {} *****\n", id);
        return repository.deleteById(id);
    }
}
