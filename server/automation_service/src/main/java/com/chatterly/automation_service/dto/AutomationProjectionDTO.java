package com.chatterly.automation_service.dto;

import java.net.http.WebSocket.Listener;
import java.time.LocalDateTime;
import java.util.List;

import com.chatterly.automation_service.entity.Keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomationProjectionDTO {
    private String id;
    private String name;
    private boolean active;

}
