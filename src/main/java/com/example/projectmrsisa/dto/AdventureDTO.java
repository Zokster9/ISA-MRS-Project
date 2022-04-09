package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Adventure;

import java.util.List;

public class AdventureDTO {
    private String name;
    private String description;
    private List<String> pictures;
    private List<String> rulesOfConduct;
    private double price;
    private String instructorBiography;
    private List<String> fishingEquipment;
    private int maxNumOfPeople;
    private String reservationCancellationConditions;
    private boolean isDeleted;

    public AdventureDTO(){

    }

    public AdventureDTO(String name, String description, List<String> pictures, List<String> rulesOfConduct, double price,
                        String instructorBiography, List<String> fishingEquipment, int maxNumOfPeople, String reservationCancellationConditions){
        this.name = name;
        this.description = description;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.price = price;
        this.instructorBiography = instructorBiography;
        this.fishingEquipment = fishingEquipment;
        this.maxNumOfPeople = maxNumOfPeople;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.isDeleted = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public List<String> getRulesOfConduct() {
        return rulesOfConduct;
    }

    public double getPrice() {
        return price;
    }

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public List<String> getFishingEquipment() {
        return fishingEquipment;
    }

    public int getMaxNumOfPeople() {
        return maxNumOfPeople;
    }

    public String getReservationCancellationConditions() {
        return reservationCancellationConditions;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public void setRulesOfConduct(List<String> rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInstructorBiography(String instructorBiography) {
        this.instructorBiography = instructorBiography;
    }

    public void setFishingEquipment(List<String> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public void setMaxNumOfPeople(int maxNumOfPeople) {
        this.maxNumOfPeople = maxNumOfPeople;
    }

    public void setReservationCancellationConditions(String reservationCancellationConditions) {
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
