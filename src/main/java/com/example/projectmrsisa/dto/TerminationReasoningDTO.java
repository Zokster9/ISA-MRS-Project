package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.model.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class TerminationReasoningDTO {

    private Integer id;

    private Integer userId;

    private String terminationReasoning;

    public TerminationReasoningDTO() {}

    public TerminationReasoningDTO(Integer id, Integer userId, String terminationReasoning) {
        this.id = id;
        this.userId = userId;
        this.terminationReasoning = terminationReasoning;
    }

    public TerminationReasoningDTO(TerminationReasoning terminationReasoning) {
        this.id = terminationReasoning.getId();
        this.userId = terminationReasoning.getUser().getId();
        this.terminationReasoning = terminationReasoning.getTerminationReasoning();
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

    public String getTerminationReasoning() {
        return terminationReasoning;
    }

    public void setTerminationReasoning(String terminationReasoning) {
        this.terminationReasoning = terminationReasoning;
    }
}
