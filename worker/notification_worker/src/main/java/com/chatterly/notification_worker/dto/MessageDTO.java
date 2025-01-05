package com.chatterly.notification_worker.dto;

public class MessageDTO {

    private String email;
    private String token;

    public MessageDTO() {
    }

    public MessageDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
