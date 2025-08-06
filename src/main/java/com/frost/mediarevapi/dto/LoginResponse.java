package com.frost.mediarevapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean login;
    private UserInfo user;
    private String accessToken;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Integer id;
        private String userId;
        private String username;
        private String email;
        private Integer accountType;
        private String role;
        private String picturePath;
        private String status;
    }
}
