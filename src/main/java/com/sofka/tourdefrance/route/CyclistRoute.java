package com.sofka.tourdefrance.route;

import com.sofka.tourdefrance.model.CyclistDto;
import com.sofka.tourdefrance.usecases.cyclist.*;
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
public class CyclistRoute {
    static final String ENDPOINT = "/api/v1/cyclist";

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    @Bean
    public RouterFunction<ServerResponse> createCyclist(CreateCyclistUseCase useCase) {
        return route(
                POST(ENDPOINT).and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDto.class)
                        .flatMap(useCase::apply)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> readAllCyclists(ReadAllCyclistUseCase useCase) {
        return route(GET(ENDPOINT), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), CyclistDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> readCyclistByNumber(ReadCyclistByNumberUseCase useCase) {
        return route(GET(ENDPOINT + "/number/{number}"), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(tryParse(request.pathVariable("number"))), CyclistDto.class))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> readCyclistByTeamCode(ReadCyclistByTeamIdUseCase useCase) {
        return route(GET(ENDPOINT + "/team/{id}"), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(request.pathVariable("id")), CyclistDto.class))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> updateCyclist(UpdateCyclistUseCase useCase) {
        return route(PUT(ENDPOINT), request -> request.bodyToMono(CyclistDto.class)
                .flatMap(useCase)
                .flatMap(result -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(result))
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                        .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteCyclistById(DeleteCyclistUseCase useCase) {
        return route(DELETE(ENDPOINT + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), Void.class)));
    }
}
