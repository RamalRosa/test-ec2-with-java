package com.example.demo.controllers;

import com.example.demo.dto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public Mono<String> addUser(@RequestBody AddUserRequest user) {

        log.info("addUser function");
        log.info(String.valueOf(user));
        return webClientBuilder.build()
                .post()
                .uri("/api/users")
                .body(Mono.just(user), AddUserRequest.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping
    public Mono<String> getUsers() {

        log.info("get user list function");
        Mono<String> response = webClientBuilder.build()
                .get()
                .uri("/api/users")
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(res -> log.info("Response: {}", res))
                .doOnError(error -> log.error("Error: ", error));

        log.info("Response: {}", response);

        return response;
    }
}
