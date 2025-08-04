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
        private String username;
        private String email;
        private String uid;
        private Integer accountType; // numeric role instead
        private String role;
        private String pictureUrl;
        private String status;
    }
}
