package com.chatterly.integration_service.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chatterly.integration_service.entity.Integrations;
import com.chatterly.integration_service.repo.IntegrationsRepository;

@Service
public class IntegrationService {

    private final IntegrationsRepository integrationsRepository;

    public IntegrationService(IntegrationsRepository integrationsRepository) {
        this.integrationsRepository = integrationsRepository;
    }

    @Cacheable(value = "integrations", key = "#userId")
    public Integrations getIntegrationByUserId(String userId) {
        return integrationsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No integration found for user"));
    }

}
