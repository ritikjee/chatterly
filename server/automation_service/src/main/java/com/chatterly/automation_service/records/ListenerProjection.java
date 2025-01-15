package com.chatterly.automation_service.records;

import com.chatterly.automation_service.enums.Listeners;

public record ListenerProjection(
                String automationId,
                Listeners listener,
                String prompt,
                String reply) {
        public ListenerProjection {
                if (automationId == null || automationId.isBlank()) {
                        throw new IllegalArgumentException("automationId cannot be null or blank");
                }
        }
}
