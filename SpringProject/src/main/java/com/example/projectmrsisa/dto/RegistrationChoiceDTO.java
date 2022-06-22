package com.example.projectmrsisa.dto;

public class RegistrationChoiceDTO {
    private Integer userId;
    private String declineReasoning;

    public RegistrationChoiceDTO(){

    }

    public RegistrationChoiceDTO(Integer userId, String declineReasoning){
        this.userId = userId;
        this.declineReasoning = declineReasoning;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDeclineReasoning() {
        return declineReasoning;
    }

    public void setDeclineReasoning(String declineReasoning) {
        this.declineReasoning = declineReasoning;
    }
}
