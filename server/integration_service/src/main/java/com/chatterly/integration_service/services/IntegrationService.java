package com.chatterly.integration_service.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import com.chatterly.integration_service.entity.Integrations;
import com.chatterly.integration_service.repo.IntegrationsRepository;

public class IntegrationService {

    private final IntegrationsRepository integrationsRepository;
    private final InstagramService instagramService;

    public IntegrationService(IntegrationsRepository integrationsRepository, InstagramService instagramService) {
        this.integrationsRepository = integrationsRepository;
        this.instagramService = instagramService;
    }

    public void refreshInstagramToken(String userId) {
        Optional<Integrations> integrations = integrationsRepository.findByUserId(userId);

        if (!integrations.isPresent())
            return;

        Integrations integration = integrations.get();

        long daysUntilExpiry = Duration.between(LocalDateTime.now(), integration.getExpiresAt()).toDays();

        if (daysUntilExpiry < 5) {
            String token = instagramService.refreshToken(integration.getToken());

            boolean updated = integrationsRepository.updateTokenAndExpirationByUserId(userId, token,
                    LocalDateTime.now().plusDays(60));

            if (!updated) {
                throw new RuntimeException("Failed to update token");
            }

        }

    }

}
