package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Admin;

public class AdminDTO {
    private Integer id;
    private Integer userId;
    private boolean isPasswordChanged;

    public AdminDTO(){

    }

    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.userId = admin.getUser().getId();
        this.isPasswordChanged = admin.isPasswordChanged();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isPasswordChanged() {
        return isPasswordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        isPasswordChanged = passwordChanged;
    }
}
