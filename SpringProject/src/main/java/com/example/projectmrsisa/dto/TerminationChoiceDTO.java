package com.example.projectmrsisa.dto;

public class TerminationChoiceDTO {
    private Integer userId;
    private String terminationChoiceReason;

    public TerminationChoiceDTO(){

    }

    public TerminationChoiceDTO(Integer userId, String terminationChoiceReason){
        this.userId = userId;
        this.terminationChoiceReason = terminationChoiceReason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTerminationChoiceReason() {
        return terminationChoiceReason;
    }

    public void setTerminationChoiceReason(String terminationChoiceReason) {
        this.terminationChoiceReason = terminationChoiceReason;
    }
}
