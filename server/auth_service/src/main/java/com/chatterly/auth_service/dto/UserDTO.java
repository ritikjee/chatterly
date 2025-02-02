package com.chatterly.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String id;
    private String email;
    private String firstname;
    private String lastname;
    private String image;

}