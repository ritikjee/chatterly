package com.chatterly.integration_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.integration_service.dto.ErrorResponseDTO;
import com.chatterly.integration_service.dto.SuccessResponseDTO;
import com.chatterly.integration_service.services.IntegrationService;

@RestController
@RequestMapping("/api/v1/integration")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping
    public ResponseEntity<?> getUserIntegration(@RequestHeader("userId") String userId) {
        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, integrationService.getIntegrationByUserId(userId)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"));
        }

    }

}
