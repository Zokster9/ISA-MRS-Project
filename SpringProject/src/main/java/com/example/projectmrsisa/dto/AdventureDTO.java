package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Adventure;
import com.example.projectmrsisa.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class AdventureDTO {
    private Integer id;
    private Integer ownerId;
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
    private String country;
    private String street;
    private String city;
    private List<String> additionalServices;
    private String serviceType;
    private double averageRating;

    public AdventureDTO(){

    }

    public AdventureDTO(String name, String description, List<String> pictures, List<String> rulesOfConduct, double price,
                        String instructorBiography, List<String> fishingEquipment, int maxNumOfPeople, String reservationCancellationConditions,
                        String country, String street, String city, List<String> additionalServices){
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
        this.country = country;
        this.street = street;
        this.city = city;
        this.additionalServices = additionalServices;
    }

    public AdventureDTO(Adventure adventure){
        this.id = adventure.getId();
        this.ownerId = adventure.getOwner().getId();
        this.name = adventure.getName();
        this.description = adventure.getDescription();
        this.pictures = new ArrayList<>(adventure.getPictures());
        this.rulesOfConduct = new ArrayList<>(adventure.getRulesOfConduct());
        this.price = adventure.getPrice();
        this.instructorBiography = adventure.getInstructorBiography();
        this.fishingEquipment = new ArrayList<>(adventure.getFishingEquipment());
        this.maxNumOfPeople = adventure.getMaxNumOfPeople();
        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
        this.isDeleted = adventure.isDeleted();
        this.country = adventure.getAddress().getCountry();
        this.city = adventure.getAddress().getCity();
        this.street = adventure.getAddress().getStreet();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : adventure.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
    }

    public AdventureDTO(Adventure adventure, double averageRating){
        this.id = adventure.getId();
        this.ownerId = adventure.getOwner().getId();
        this.name = adventure.getName();
        this.description = adventure.getDescription();
        this.pictures = new ArrayList<>(adventure.getPictures());
        this.rulesOfConduct = new ArrayList<>(adventure.getRulesOfConduct());
        this.price = adventure.getPrice();
        this.instructorBiography = adventure.getInstructorBiography();
        this.fishingEquipment = new ArrayList<>(adventure.getFishingEquipment());
        this.maxNumOfPeople = adventure.getMaxNumOfPeople();
        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
        this.isDeleted = adventure.isDeleted();
        this.country = adventure.getAddress().getCountry();
        this.city = adventure.getAddress().getCity();
        this.street = adventure.getAddress().getStreet();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : adventure.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
        this.averageRating = averageRating;
    }

    public AdventureDTO(Adventure adventure, String serviceType, double averageRating){
        this.id = adventure.getId();
        this.ownerId = adventure.getOwner().getId();
        this.name = adventure.getName();
        this.description = adventure.getDescription();
        this.pictures = new ArrayList<>(adventure.getPictures());
        this.rulesOfConduct = new ArrayList<>(adventure.getRulesOfConduct());
        this.price = adventure.getPrice();
        this.instructorBiography = adventure.getInstructorBiography();
        this.fishingEquipment = new ArrayList<>(adventure.getFishingEquipment());
        this.maxNumOfPeople = adventure.getMaxNumOfPeople();
        this.reservationCancellationConditions = adventure.getReservationCancellationConditions();
        this.isDeleted = adventure.isDeleted();
        this.country = adventure.getAddress().getCountry();
        this.city = adventure.getAddress().getCity();
        this.street = adventure.getAddress().getStreet();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : adventure.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
        this.serviceType = serviceType;
        this.averageRating = averageRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
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
