package com.chatterly.automation_service.dto;

import com.chatterly.automation_service.enums.Listeners;

import lombok.Data;

@Data
public class AddListenerDTO {

    private String automationId;
    private Listeners listener;
    private String prompt;
    private String commentReply;
}
