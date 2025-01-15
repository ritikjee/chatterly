package com.chatterly.automation_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.records.ListenerProjection;
import com.chatterly.automation_service.services.ListenerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    private final ListenerService listenerService;

    public ListenerController(ListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @PostMapping("/create-listener")
    public ResponseEntity<?> createListener(@RequestHeader("userId") String userId,
            @RequestBody ListenerProjection entity) {
        try {
            listenerService.createListener(entity, userId);

            return ResponseEntity.ok(new SuccessResponseDTO<>(HttpStatus.OK, "Listener created successfully"));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new SuccessResponseDTO<>(HttpStatus.BAD_REQUEST, e.getMessage()));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SuccessResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
        }
    }

}
