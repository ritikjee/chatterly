package com.chatterly.automation_service.records;

import java.util.ArrayList;

public record PostsInputProjection(
        String automationId,
        ArrayList<PostProjection> posts) {

}
