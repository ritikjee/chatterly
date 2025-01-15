package com.chatterly.auth_service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record DeviceDTO(
                String id,
                String ipAddress,
                String deviceType,
                String os,
                LocalDateTime loginTime,
                String deviceAgent) implements Serializable {
}
