package com.chatterly.automation_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatterly.automation_service.dto.AutomationDTO;
import com.chatterly.automation_service.repo.AutomationRepository;

@Service
public class AutomationService {

    private final AutomationRepository automationRepository;

    public AutomationService(AutomationRepository automationRepository) {
        this.automationRepository = automationRepository;
    }

    public List<AutomationDTO> getAutomations(String userId) {
        return automationRepository.findByUserId(userId);
    }

}
