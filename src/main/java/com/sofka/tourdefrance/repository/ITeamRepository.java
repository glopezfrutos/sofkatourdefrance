package com.sofka.tourdefrance.repository;

import com.sofka.tourdefrance.collection.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends ReactiveMongoRepository<Team, String> {
}
