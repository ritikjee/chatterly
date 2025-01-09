package com.chatterly.integration_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chatterly.integration_service.dto.ErrorResponseDTO;
import com.chatterly.integration_service.dto.SuccessResponseDTO;
import com.chatterly.integration_service.dto.UserDTO;
import com.chatterly.integration_service.services.IntegrationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/integration")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/refresh-instagram-token")
    public ResponseEntity<?> refreshInstagramToken(HttpServletRequest request) {

        try {
            UserDTO user = (UserDTO) request.getAttribute("user");
            integrationService.refreshInstagramToken(user.getId());
            return ResponseEntity.ok(new SuccessResponseDTO<>(HttpStatus.OK, "Token refreshed successfully"));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }

    }

}
