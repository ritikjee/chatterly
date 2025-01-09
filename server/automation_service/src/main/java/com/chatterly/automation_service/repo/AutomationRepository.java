package com.chatterly.automation_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatterly.automation_service.dto.AutomationDTO;
import com.chatterly.automation_service.entity.Automation;

public interface AutomationRepository extends JpaRepository<Automation, String> {
    List<AutomationDTO> findByUserId(String userId);
}
