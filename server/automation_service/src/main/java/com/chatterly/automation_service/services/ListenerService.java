package com.chatterly.automation_service.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Listener;
import com.chatterly.automation_service.records.ListenerProjection;
import com.chatterly.automation_service.repo.ListenerRepository;

@Service
public class ListenerService {

    private final ListenerRepository listenerRepository;
    private final AutomationService automationService;

    public ListenerService(ListenerRepository listenerRepository, AutomationService automationService) {
        this.listenerRepository = listenerRepository;
        this.automationService = automationService;
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#listener.automationId()")
    })
    public String createListener(ListenerProjection listener, String userId) {
        Listener newListener = new Listener();

        newListener.setListener(listener.listener());
        newListener.setPrompt(listener.prompt());
        newListener.setCommentReply(listener.reply());
        newListener.setAutomation(
                automationService.getAutomationById(listener.automationId(), userId));

        listenerRepository.save(newListener);

        return "Listener created successfully";

    }
}
