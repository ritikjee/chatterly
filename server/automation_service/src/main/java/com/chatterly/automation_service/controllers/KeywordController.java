package com.chatterly.automation_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.ErrorResponseDTO;
import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.records.KeywordProjection;
import com.chatterly.automation_service.services.KeywordService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addKeyword(@RequestBody KeywordProjection entity,
            @RequestHeader("userId") String userId) {
        try {

            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, keywordService.addKeyword(entity, userId)));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteKeyword(@RequestBody KeywordProjection entity,
            @RequestHeader("userId") String userId) {
        try {

            return ResponseEntity
                    .ok(new SuccessResponseDTO<>(HttpStatus.OK, keywordService.deleteKeyword(entity, userId)));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"));
        }
    }

}
