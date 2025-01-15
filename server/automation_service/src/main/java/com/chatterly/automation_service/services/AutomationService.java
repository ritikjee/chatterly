package com.chatterly.automation_service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.repo.AutomationRepository;

@Service
public class AutomationService {

    private final AutomationRepository automationRepository;

    public AutomationService(AutomationRepository automationRepository) {
        this.automationRepository = automationRepository;
    }

    @Cacheable(value = "automations", key = "#userId")
    public List<Automation> getAutomations(String userId) {
        return automationRepository.findByUserId(userId);
    }

    @CacheEvict(value = "automations", key = "#userId")
    public String createAutomation(String userId) {

        Automation automation = new Automation();
        automation.setUserId(userId);
        automation.setName("Untitled");
        automation.setActive(false);
        automation.setCreatedAt(LocalDateTime.now());

        try {
            automationRepository.save(automation);
            return "Automation created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#id")
    })
    public boolean updateAutomationName(String id, String userId, String name) {
        return automationRepository.updateNameById(id, userId, name) > 0;
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#id")
    })
    public boolean updateAutomationActive(String id, String userId, boolean active) {
        return automationRepository.updateActiveById(id, userId, active) > 0;
    }

    @Cacheable(value = "automation-details", key = "#userId+'::'+#id")
    public Automation getAutomationById(String id, String userId) {
        return automationRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("No automation found"));
    }

}
