package com.chatterly.automation_service.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.entity.Keyword;
import com.chatterly.automation_service.records.KeywordProjection;
import com.chatterly.automation_service.repo.AutomationRepository;
import com.chatterly.automation_service.repo.KeywordRepository;

@Service
public class KeywordService {

    private final AutomationRepository automationRepository;
    private final KeywordRepository keywordRepository;

    public KeywordService(AutomationRepository automationRepository, KeywordRepository keywordRepository) {
        this.automationRepository = automationRepository;
        this.keywordRepository = keywordRepository;
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#keywordProjection.automationId()")
    })
    public String addKeyword(KeywordProjection keywordProjection, String userId) {
        Automation automation = automationRepository.findByIdAndUserId(keywordProjection.automationId(), userId)
                .orElseThrow(
                        () -> new RuntimeException("Automation not found"));

        keywordRepository.save(new Keyword(keywordProjection.keywordId(), automation));

        return "Keyword added successfully";

    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#keywordProjection.automationId()")
    })
    public String deleteKeyword(KeywordProjection keywordProjection, String userId) {

        keywordRepository.deleteById(keywordProjection.keywordId());

        return "Keyword deleted successfully";
    }
}
