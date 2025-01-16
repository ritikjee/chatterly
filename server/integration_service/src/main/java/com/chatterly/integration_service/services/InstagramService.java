package com.chatterly.integration_service.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chatterly.integration_service.dto.InstagramResponseDTO;
import com.chatterly.integration_service.entity.Integrations;

@Service
public class InstagramService {

    @Value("${instagram.base.url}")
    private String instagram_base_url;

    private final IntegrationService integrationService;

    public InstagramService(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Cacheable(value = "instagram-posts", key = "#userId")
    public ArrayList<InstagramResponseDTO> getInstagramPosts(String userId) {
        Integrations integrations = integrationService.getIntegrationByUserId(userId);

        String token = integrations.getToken();

        String url = String.format(
                "%s/me/media?fields=id,caption,media_url,media_type,timestamp&limit=10&access_token=%s",
                instagram_base_url, token);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<InstagramResponseDTO>> response = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<InstagramResponseDTO>>() {
                });

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get Instagram posts");
        }

    }

}
