package com.chatterly.automation_service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.repo.AutomationRepository;

@Service
public class AutomationService {

    private final AutomationRepository automationRepository;

    public AutomationService(AutomationRepository automationRepository) {
        this.automationRepository = automationRepository;
    }

    public List<Automation> getAutomations(String userId) {
        return automationRepository.findByUserId(userId);
    }

    public boolean createAutomation(String userId) {

        Automation automation = new Automation();
        automation.setUserId(userId);
        automation.setName("Untitled");
        automation.setActive(false);
        automation.setCreatedAt(LocalDateTime.now());

        try {
            automationRepository.save(automation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAutomationName(String id, String userId, String name) {
        return automationRepository.updateNameById(id, userId, name);
    }

    public boolean updateAutomationActive(String id, String userId, boolean active) {
        return automationRepository.updateActiveById(id, userId, active);
    }

}
