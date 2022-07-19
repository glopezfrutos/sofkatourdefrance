package com.sofka.tourdefrance.repository;

import com.sofka.tourdefrance.collection.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
}
