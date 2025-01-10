package com.chatterly.api_gateway_v2.services;

import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.chatterly.api_gateway_v2.dto.SuccessResponseDTO;
import com.chatterly.api_gateway_v2.dto.UserDTO;

import reactor.core.publisher.Mono;

@Service
public class ReactiveUserService {

    private final WebClient webClient;

    public ReactiveUserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Mono<SuccessResponseDTO<UserDTO>> getUserDetails(ServerWebExchange exchange) {
        String token = Optional.ofNullable(exchange.getRequest()
                .getCookies()
                .getFirst("token"))
                .map(cookie -> cookie.getValue())
                .orElse("");

        return webClient.get()
                .uri("/api/v1/user/me")
                .header(HttpHeaders.COOKIE, "token=" + token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SuccessResponseDTO<UserDTO>>() {
                });
    }

}
