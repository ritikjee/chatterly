package com.chatterly.integration_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.integration_service.dto.ErrorResponseDTO;
import com.chatterly.integration_service.dto.SuccessResponseDTO;
import com.chatterly.integration_service.services.InstagramService;

@RestController
@RequestMapping("/instagram")
public class InstagramController {

    private final InstagramService instagramService;

    public InstagramController(InstagramService instagramService) {
        this.instagramService = instagramService;
    }

    @GetMapping("/get-posts")
    public ResponseEntity<?> getInstagramPosts(@RequestHeader("userId") String userId) {

        try {
            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, instagramService.getInstagramPosts(userId)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponseDTO(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ErrorResponseDTO(500, "Internal server error"));
        }

    }

}
