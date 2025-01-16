package com.chatterly.automation_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.automation_service.dto.ErrorResponseDTO;
import com.chatterly.automation_service.dto.SuccessResponseDTO;
import com.chatterly.automation_service.records.PostsInputProjection;
import com.chatterly.automation_service.services.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@RequestHeader("userId") String userId,
            @RequestBody PostsInputProjection entity) {

        try {

            if (entity.automationId() == null ||
                    entity.automationId().isEmpty() ||
                    entity.posts() == null ||
                    entity.posts().size() == 0) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponseDTO(400, "Please provide automationId and posts"));
            }

            return ResponseEntity.ok(new SuccessResponseDTO<>(HttpStatus.OK, postService.createPost(userId, entity)));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(500, "Internal Server error"));
        }

    }

}
