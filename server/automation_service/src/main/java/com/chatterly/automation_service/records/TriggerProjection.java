package com.chatterly.automation_service.records;

import java.io.Serializable;
import java.util.ArrayList;

public record TriggerProjection(
        String automationId,
        ArrayList<String> triggers) implements Serializable {

}
