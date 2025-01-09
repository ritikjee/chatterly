package com.chatterly.integration_service.services;

import org.springframework.stereotype.Service;

import com.chatterly.integration_service.client.InstagramClient;
import com.chatterly.integration_service.dto.InstagramTokenResponse;

@Service
public class InstagramService {

    private final InstagramClient instagramClient;

    public InstagramService(InstagramClient instagramClient) {
        this.instagramClient = instagramClient;
    }

    public String refreshToken(String token) {
        InstagramTokenResponse response = instagramClient.refreshAccessToken("ig_refresh_token", token);
        if (response != null) {
            return response.getAccessToken();
        }
        throw new RuntimeException("Failed to refresh token");
    }

}
