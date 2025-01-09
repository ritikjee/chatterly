package com.chatterly.automation_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.chatterly.automation_service.entity.Keyword;
import com.chatterly.automation_service.entity.Listener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutomationDTO {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
    private List<Keyword> keywords;
    private Listener listener;
}
