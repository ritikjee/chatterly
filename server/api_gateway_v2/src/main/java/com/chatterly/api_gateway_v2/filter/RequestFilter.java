package com.chatterly.api_gateway_v2.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.chatterly.api_gateway_v2.dto.ErrorResponseDTO;
import com.chatterly.api_gateway_v2.services.ReactiveUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class RequestFilter implements GlobalFilter {

    private final ObjectMapper objectMapper;
    private final ReactiveUserService userService;

    public RequestFilter(ObjectMapper objectMapper, ReactiveUserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestURI = exchange.getRequest().getURI().getPath();

        // Allow requests to /api/v1/auth/ to bypass the filter
        if (requestURI.contains("/api/v1/auth/")) {
            return chain.filter(exchange);
        }

        List<String> cookies = exchange.getRequest().getHeaders().getOrEmpty("Cookie");
        if (cookies.isEmpty()) {
            return sendErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Not authorized: No cookies found");
        }

        String token = cookies.stream()
                .filter(cookie -> cookie.contains("token="))
                .findFirst()
                .map(cookie -> cookie.split("=")[1])
                .orElse("");

        if (token.isEmpty()) {
            return sendErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Not authorized: No token found");
        }

        return userService.getUserDetails(exchange)
                .flatMap(response -> {
                    if (response == null || response.getData() == null) {
                        return sendErrorResponse(exchange, HttpStatus.UNAUTHORIZED,
                                "Not authorized: Invalid user data");
                    }
                    ServerHttpRequest request = exchange.getRequest()
                            .mutate()
                            .header("userId", response.getData().getId())
                            .header("email", response.getData().getEmail())
                            .build();

                    return chain.filter(exchange.mutate().request(request).build());
                })
                .onErrorResume(error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                    return sendErrorResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
                });
    }

    private Mono<Void> sendErrorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(status.value(), message);
        String errorResponseJson;
        try {
            errorResponseJson = objectMapper.writeValueAsString(errorResponse);
        } catch (Exception e) {
            errorResponseJson = "{\"status\":" + status.value() + ",\"message\":\"" + message + "\"}";
        }

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");

        byte[] bytes = errorResponseJson.getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(bytes)));
    }
}
