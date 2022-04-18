package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RetreatDTO {

    private int id;
    private int ownerId;
    private String name;
    private String description;
    private String country;
    private String city;
    private String street;
    private List<String> pictures;
    private List<String> rulesOfConduct;
    private int numOfRooms;
    private int numOfBeds;
    private List<String> additionalServices;

    public RetreatDTO() {}

    public RetreatDTO(String name, String description, String country, String city, String street, List<String> pictures, List<String> rulesOfConduct, int numOfRooms, int numOfBeds, List<String> additionalServices) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.street = street;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.numOfRooms = numOfRooms;
        this.numOfBeds = numOfBeds;
        this.additionalServices = additionalServices;
    }

    public RetreatDTO(Retreat retreat) {
        this.id = retreat.getId();
        this.ownerId = retreat.getOwner().getId();
        this.name= retreat.getName();
        this.description = retreat.getDescription();
        this.country = retreat.getAddress().getCountry();
        this.city = retreat.getAddress().getCity();
        this.street = retreat.getAddress().getStreet();
        this.pictures = new ArrayList<>(retreat.getPictures());
        this.rulesOfConduct = new ArrayList<>(retreat.getRulesOfConduct());
        this.numOfBeds = retreat.getNumOfBeds();
        this.numOfRooms = retreat.getNumOfRooms();
        this.additionalServices = new ArrayList<>();
        for (Tag t : retreat.getAdditionalServices()) {
            this.additionalServices.add(t.getName());
        }
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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<String> getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(List<String> rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
    }
}