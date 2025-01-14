package com.chatterly.automation_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.ErrorResponseDTO;
import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.dto.UpdateAutomationDTO;
import com.chatterly.automation_service.services.AutomationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/automation")
public class AutomationController {

    private final AutomationService automationService;

    public AutomationController(AutomationService automationService) {
        this.automationService = automationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAutomations(@RequestHeader("userId") String userId) {

        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, automationService.getAutomations(userId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAutomation(@RequestHeader("userId") String userId) {

        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, automationService.createAutomation(userId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable String id, @RequestBody UpdateAutomationDTO data,
            @RequestHeader("userId") String userId) {
        try {
            if (data.getName() != null) {
                automationService.updateAutomationName(id, userId, data.getName());
            }
            if (data.isActive()) {
                automationService.updateAutomationActive(id, userId, data.isActive());
            }

            return ResponseEntity.ok(new SuccessResponseDTO<>(HttpStatus.OK, "Automation updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
