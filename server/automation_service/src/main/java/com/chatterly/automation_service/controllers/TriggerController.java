package com.chatterly.automation_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.ErrorResponseDTO;
import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.records.TriggerProjection;
import com.chatterly.automation_service.services.TriggerService;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    private final TriggerService triggerService;

    public TriggerController(TriggerService triggerService) {
        this.triggerService = triggerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveTrigger(@RequestHeader("userId") String userId,
            @RequestBody TriggerProjection triggerProjection) {
        try {
            System.out.println("TriggerController.saveTrigger() userId: " + triggerProjection.automationId());
            return ResponseEntity.ok(new SuccessResponseDTO<>(HttpStatus.OK,
                    triggerService.createTrigger(triggerProjection.automationId(), userId,
                            triggerProjection.triggers())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }

    }

}
