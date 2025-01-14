package com.chatterly.automation_service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class AutomationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
    private List<KeywordDTO> keywords;
    private List<PostDTO> posts;

    @Data
    public static class KeywordDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id;
        private String word;
    }

    @Data
    public static class PostDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id;
        private String content;
    }
}
