package com.chatterly.integration_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatterly.integration_service.dto.InstagramTokenResponse;

@FeignClient(name = "instagram-client", url = "${instagram.base.url}")
public interface InstagramClient {

    @GetMapping("/refresh_access_token")
    InstagramTokenResponse refreshAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("access_token") String accessToken);

}
