package com.sofka.tourdefrance.usecases.team;

import com.sofka.tourdefrance.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteTeamByIdUseCase {
    private final ITeamRepository repository;

    public Mono<Void> apply(String id) {
        log.info("\n***** Deleting team by Id: {} *****\n", id);
        return repository.deleteById(id);
    }
}
