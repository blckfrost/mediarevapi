package com.frost.mediarevapi.model;

public class User {
    private String username;
    private String uid;
    private String password;
    private String email;
    private Integer accountType;
    private String picturePath;
    private String acType;

    public User(){}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public  String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAccountType() { return accountType; }
    public void setAccountType(Integer accountType) { this.accountType = accountType; }

    public String getPicturePath() { return picturePath; }
    public void setPicturePath(String picturePath) { this.picturePath = picturePath; }

    public String getAcType() { return acType; }
    public void setAcType(String acType) { this.acType = acType; }

}
