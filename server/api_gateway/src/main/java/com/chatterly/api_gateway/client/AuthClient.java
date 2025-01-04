package com.chatterly.api_gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.chatterly.api_gateway.dto.UserDTO;

@FeignClient(name = "auth-service", url = "http://localhost:8081", path = "/api/v1/user")
public interface AuthClient {

    @GetMapping("/me")
    UserDTO getAuthenticatedUser();

}
