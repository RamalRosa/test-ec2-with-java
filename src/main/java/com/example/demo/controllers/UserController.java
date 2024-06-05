package com.example.demo.controllers;

import com.example.demo.dto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public Mono<String> addUser(@RequestBody AddUserRequest user) {

        return webClientBuilder.build()
                .post()
                .uri("/api/users")
                .body(Mono.just(user), AddUserRequest.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping
    public Mono<String> getUsers() {

        return webClientBuilder.build()
                .get()
                .uri("/api/users")
                .retrieve()
                .bodyToMono(String.class);
    }
}
