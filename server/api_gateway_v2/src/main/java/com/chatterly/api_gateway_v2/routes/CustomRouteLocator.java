package com.chatterly.api_gateway_v2.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteLocator {

        @Bean
        public RouteLocator routeLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("auth_service", r -> r.path("/api/v1/auth/**")
                                                .uri("http://localhost:8081"))
                                .route("user_service", r -> r.path("/api/v1/user/**")
                                                .uri("http://localhost:8081"))
                                .build();

        }

}
