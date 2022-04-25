package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Service;

import java.util.Set;

public class ServiceDTO {
    private int id;
    private String name;
    private String description;
    private Set<String> pictures;

    public ServiceDTO(){

    }

    public ServiceDTO(Service service){
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.pictures = service.getPictures();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getPictures() {
        return pictures;
    }

    public void setPictures(Set<String> pictures) {
        this.pictures = pictures;
    }
}
