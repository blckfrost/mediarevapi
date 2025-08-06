package com.frost.mediarevapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String user_id;
    private String username;
    private String password;
    private String email;
    private Integer accountType;
    private String picturePath;
    private String status;
}