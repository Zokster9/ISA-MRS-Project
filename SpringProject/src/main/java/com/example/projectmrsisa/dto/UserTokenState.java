package com.example.projectmrsisa.dto;

public class UserTokenState {

    private String accessToken;
    private Long expiresIn;
    private String roleName;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.roleName = null;
    }

    public UserTokenState(String accessToken, long expiresIn, String roleName) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.roleName = roleName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
