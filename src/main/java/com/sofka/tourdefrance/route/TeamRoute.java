package com.sofka.tourdefrance.route;

import com.sofka.tourdefrance.model.TeamDto;
import com.sofka.tourdefrance.usecases.team.*;
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
public class TeamRoute {
    static final String ENDPOINT = "/api/v1/team";


    @Bean
    public RouterFunction<ServerResponse> createTeam(CreateTeamUseCase useCase) {
        return route(
                POST(ENDPOINT).and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDto.class)
                        .flatMap(useCase::apply)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> readAllTeams(ReadAllTeamsUseCase useCase) {
        return route(GET(ENDPOINT), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), TeamDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> readTeamByTeamShortName(ReadTeamByTeamShortNameUseCase useCase) {
        return route(GET(ENDPOINT + "/{shortname}"), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(request.pathVariable("shortname")), TeamDto.class))
                        .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                                .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }


    @Bean
    public RouterFunction<ServerResponse> updateTeam(UpdateTeamUseCase useCase) {
        return route(PUT(ENDPOINT), request -> request.bodyToMono(TeamDto.class)
                .flatMap(useCase)
                .flatMap(result -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(result))
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage())
                        .flatMap(s -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.TEXT_PLAIN).bodyValue(s))));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteTeamById(DeleteTeamByIdUseCase useCase) {
        return route(DELETE(ENDPOINT + "/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), Void.class)));
    }
}
