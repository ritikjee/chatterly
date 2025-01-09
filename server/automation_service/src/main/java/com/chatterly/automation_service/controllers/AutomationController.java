package com.chatterly.automation_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.ErrorResponseDTO;
import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.dto.UserDTO;
import com.chatterly.automation_service.services.AutomationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/automation")
public class AutomationController {

    private final AutomationService automationService;

    public AutomationController(AutomationService automationService) {
        this.automationService = automationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAutomations(HttpServletRequest request) {

        UserDTO user = (UserDTO) request.getAttribute("user");
        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, automationService.getAutomations(user.getId())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

}
