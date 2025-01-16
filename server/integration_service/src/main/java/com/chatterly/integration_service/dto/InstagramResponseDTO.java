package com.chatterly.integration_service.dto;

import java.io.Serializable;

public class InstagramResponseDTO implements Serializable {

    private String id;
    private String caption;
    private String media_url;
    private String media_type;
    private String timestamp;

    public InstagramResponseDTO() {
    }

    public InstagramResponseDTO(String id, String caption, String media_url, String media_type, String timestamp) {
        this.id = id;
        this.caption = caption;
        this.media_url = media_url;
        this.media_type = media_type;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
