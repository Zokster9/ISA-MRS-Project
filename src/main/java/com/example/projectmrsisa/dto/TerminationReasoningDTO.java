package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.TerminationReasoning;

public class TerminationReasoningDTO {

    private int userId;
    private String name;
    private String surname;
    private String terminationReasoning;

    public TerminationReasoningDTO(){

    }

    public TerminationReasoningDTO(TerminationReasoning terminationReasoning){
        this.userId = terminationReasoning.getUser().getId();
        this.name = terminationReasoning.getUser().getName();
        this.surname =terminationReasoning.getUser().getSurname();
        this.terminationReasoning = terminationReasoning.getTerminationReasoning();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTerminationReasoning() {
        return terminationReasoning;
    }

    public void setTerminationReasoning(String terminationReasoning) {
        this.terminationReasoning = terminationReasoning;
    }
}
