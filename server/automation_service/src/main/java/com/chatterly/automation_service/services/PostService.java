package com.chatterly.automation_service.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.entity.Post;
import com.chatterly.automation_service.records.PostsInputProjection;
import com.chatterly.automation_service.repo.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AutomationService automationService;

    public PostService(PostRepository postRepository, AutomationService automationService) {
        this.postRepository = postRepository;
        this.automationService = automationService;
    }

    @Caching(evict = {
            @CacheEvict(value = "automations", key = "#userId"),
            @CacheEvict(value = "automation-details", key = "#userId+'::'+#post.automationId()")
    })
    public String createPost(String userId, PostsInputProjection post) {
        Automation automation = automationService.getAutomationById(post.automationId(), userId);

        post.posts().forEach(postProjection -> {
            postRepository.save(new Post(
                    postProjection.postid(),
                    postProjection.caption(),
                    postProjection.media(),
                    postProjection.mediaType(),
                    automation));
        });

        return "Post Added successfully";
    }

}
