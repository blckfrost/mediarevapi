package com.frost.mediarevapi.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String accountType;
    private String picturePath;
}
