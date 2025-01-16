package com.chatterly.automation_service.services;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.entity.Trigger;
import com.chatterly.automation_service.repo.TriggerRepository;

@Service
public class TriggerService {

    private final AutomationService automationService;
    private final TriggerRepository triggerRepository;

    public TriggerService(AutomationService automationService, TriggerRepository triggerRepository) {
        this.automationService = automationService;
        this.triggerRepository = triggerRepository;
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#automationId")
    })
    public String createTrigger(String automationId, String userId, ArrayList<String> triggers) {
        Automation automation = automationService.getAutomationById(automationId, userId);

        triggers.forEach(trigger -> {
            triggerRepository.save(new Trigger(trigger, automation));
        });
        return "Triggers created successfully";

    }

}
