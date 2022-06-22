package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Tag;

public class TagDTO {
    private String name;

    public TagDTO() {
    }

    public TagDTO(Tag tag) {
        this.name = tag.getName();
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
