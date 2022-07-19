package com.sofka.tourdefrance.route;

import com.sofka.tourdefrance.model.CountryDto;
import com.sofka.tourdefrance.usecases.country.CreateCountryUseCase;
import com.sofka.tourdefrance.usecases.country.DeleteCountryUseCase;
import com.sofka.tourdefrance.usecases.country.ReadAllCountriesUseCase;
import com.sofka.tourdefrance.usecases.country.UpdateCountryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CountryRoute {
    static final String ENDPOINT = "/api/v1/country";

    @Bean
    public RouterFunction<ServerResponse> createCountry(CreateCountryUseCase useCase) {
        return route(
                POST(ENDPOINT).and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CountryDto.class)
                        .flatMap(useCase::apply)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> readAllCountries(ReadAllCountriesUseCase useCase) {
        return route(GET(ENDPOINT), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), CountryDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateCountry(UpdateCountryUseCase useCase) {
        return route(PUT(ENDPOINT), request -> request.bodyToMono(CountryDto.class)
                .flatMap(useCase)
                .flatMap(result -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(result))
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                        .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteCountryById(DeleteCountryUseCase useCase) {
        return route(DELETE(ENDPOINT + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), Void.class)));
    }
}
