package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Service;

import java.util.Set;

public class ServiceDTO {
    private int id;
    private String name;
    private String description;
    private String country;
    private String city;
    private String street;
    private Set<String> pictures;

    public ServiceDTO(){

    }

    public ServiceDTO(Service service){
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.country = service.getAddress().getCountry();
        this.city = service.getAddress().getCity();
        this.street = service.getAddress().getStreet();
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPictures(Set<String> pictures) {
        this.pictures = pictures;
    }
}
