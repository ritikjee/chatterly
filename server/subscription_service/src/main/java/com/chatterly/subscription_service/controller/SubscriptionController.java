package com.chatterly.subscription_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.subscription_service.dto.ErrorResponseDTO;
import com.chatterly.subscription_service.dto.SuccessResponseDTO;
import com.chatterly.subscription_service.services.SubscriptionService;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<?> getSubscription(@RequestHeader("userId") String userId) {
        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, subscriptionService.getSubscriptionByUserId(userId)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ErrorResponseDTO(500, "Internal server error"));
        }
    }

}
