package com.chatterly.automation_service.records;

import com.chatterly.automation_service.entity.Automation;
import com.chatterly.automation_service.enums.MediaType;

public record PostProjection(
        String postid,
        String caption,
        String media,
        MediaType mediaType) {

    public Object toPost(Automation automation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toPost'");
    }

}
