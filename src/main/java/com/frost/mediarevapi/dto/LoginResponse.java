package com.frost.mediarevapi.dto;

public class LoginResponse {
    private boolean login;
    private UserInfo user;
    private String accessToken;

    public static class UserInfo {
        private String username;
        private String email;
        private Integer id;
        private String Role;
        private String pictureUrl;

        public UserInfo() {}

        public UserInfo(String username, String email, Integer id, String role, String pictureUrl) {
            this.username = username;
            this.email = email;
            this.id = id;
            this.Role = role;
            this.pictureUrl = pictureUrl;
        }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getRole() { return Role; }
        public void setRole(String role) { this.Role = role; }

        public String getPictureUrl() { return pictureUrl; }
        public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }
    }

    public LoginResponse() {}
    public LoginResponse(boolean login, UserInfo user, String accessToken) {
        this.login = login;
        this.user = user;
        this.accessToken = accessToken;
    }

    public boolean isLogin() { return login; }
    public void setLogin(boolean login) { this.login = login; }

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

}
