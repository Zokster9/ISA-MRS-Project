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
    private Set<String> rulesOfConduct;
    private double price;
    private String ownerName;
    private String ownerSurname;
    private String serviceType;
    private double averageRating;

    public ServiceDTO() {

    }

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.country = service.getAddress().getCountry();
        this.city = service.getAddress().getCity();
        this.street = service.getAddress().getStreet();
        this.pictures = service.getPictures();
        this.rulesOfConduct = service.getRulesOfConduct();
        this.price = service.getPrice();
        this.ownerName = service.getOwner().getName();
        this.ownerSurname = service.getOwner().getSurname();
        this.city = service.getAddress().getCity();
        this.street = service.getAddress().getStreet();
        this.country = service.getAddress().getCountry();
    }

    public ServiceDTO(Service service, String serviceType, double averageRating) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.country = service.getAddress().getCountry();
        this.city = service.getAddress().getCity();
        this.street = service.getAddress().getStreet();
        this.pictures = service.getPictures();
        this.rulesOfConduct = service.getRulesOfConduct();
        this.price = service.getPrice();
        this.ownerName = service.getOwner().getName();
        this.ownerSurname = service.getOwner().getSurname();
        this.city = service.getAddress().getCity();
        this.street = service.getAddress().getStreet();
        this.country = service.getAddress().getCountry();
        this.serviceType = serviceType;
        this.averageRating = averageRating;
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

    public Set<String> getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(Set<String> rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
