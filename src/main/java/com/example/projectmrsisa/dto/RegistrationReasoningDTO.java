package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.RegistrationReasoning;

public class RegistrationReasoningDTO {
    private Integer id;
    private String registrationReason;
    private Integer privilegedUserId;

    public RegistrationReasoningDTO(){

    }

    public RegistrationReasoningDTO(RegistrationReasoning registrationReasoning){
        id = registrationReasoning.getId();
        registrationReason = registrationReasoning.getRegistrationReasoning();
        privilegedUserId = registrationReasoning.getPrivilegedUser().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }

    public Integer getPrivilegedUserId() {
        return privilegedUserId;
    }

    public void setPrivilegedUserId(Integer privilegedUserId) {
        this.privilegedUserId = privilegedUserId;
    }
}
