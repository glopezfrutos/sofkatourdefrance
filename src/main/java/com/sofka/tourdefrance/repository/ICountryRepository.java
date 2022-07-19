package com.sofka.tourdefrance.repository;

import com.sofka.tourdefrance.collection.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends ReactiveMongoRepository<Country, String> {
}
