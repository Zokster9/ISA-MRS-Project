package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class ShipDTO {

    private int id;
    private int owner_id;
    private String name;
    private String description;
    private String country;
    private String city;
    private String street;
    private double price;
    private List<String> pictures;
    private List<String> rulesOfConduct;
    private String type;
    private double length;
    private String engineNum;
    private int enginePower;
    private String maxSpeed;
    private List<String> navigationEquipment;
    private int capacity;
    private List<String> fishingEquipment;
    private String reservationCancellationConditions;
    private List<String> additionalServices;
    private String serviceType;
    private double averageRating;

    public ShipDTO() {}

    public ShipDTO(int id, int owner_id, String name, String description, String country, String city, String street,
                   double price, List<String> pictures, List<String> rulesOfConduct, String type, double length,
                   String engineNum, int enginePower, String maxSpeed, List<String> navigationEquipment, int capacity,
                   List<String> fishingEquipment, String reservationCancellationConditions, List<String> additionalServices) {
        this.id = id;
        this.owner_id = owner_id;
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.street = street;
        this.price = price;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.type = type;
        this.length = length;
        this.engineNum = engineNum;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
        this.reservationCancellationConditions = reservationCancellationConditions;
        this.additionalServices = additionalServices;
    }

    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.owner_id = ship.getOwner().getId();
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.country = ship.getAddress().getCountry();
        this.city = ship.getAddress().getCity();
        this.street = ship.getAddress().getStreet();
        this.pictures = new ArrayList<>(ship.getPictures());
        this.price = ship.getPrice();
        this.rulesOfConduct = new ArrayList<>(ship.getRulesOfConduct());
        this.type = ship.getType();
        this.length = ship.getLength();
        this.engineNum = ship.getEngineNum();
        this.enginePower = ship.getEnginePower();
        this.maxSpeed = ship.getMaxSpeed();
        this.navigationEquipment = new ArrayList<>(ship.getNavigationEquipment());
        this.capacity = ship.getCapacity();
        this.fishingEquipment = new ArrayList<>(ship.getFishingEquipment());
        this.reservationCancellationConditions = ship.getReservationCancellationConditions();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : ship.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
    }

    public ShipDTO(Ship ship, double averageRating) {
        this.id = ship.getId();
        this.owner_id = ship.getOwner().getId();
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.country = ship.getAddress().getCountry();
        this.city = ship.getAddress().getCity();
        this.street = ship.getAddress().getStreet();
        this.pictures = new ArrayList<>(ship.getPictures());
        this.price = ship.getPrice();
        this.rulesOfConduct = new ArrayList<>(ship.getRulesOfConduct());
        this.type = ship.getType();
        this.length = ship.getLength();
        this.engineNum = ship.getEngineNum();
        this.enginePower = ship.getEnginePower();
        this.maxSpeed = ship.getMaxSpeed();
        this.navigationEquipment = new ArrayList<>(ship.getNavigationEquipment());
        this.capacity = ship.getCapacity();
        this.fishingEquipment = new ArrayList<>(ship.getFishingEquipment());
        this.reservationCancellationConditions = ship.getReservationCancellationConditions();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : ship.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
        this.averageRating = averageRating;
    }

    public ShipDTO(Ship ship, String serviceType, double averageRating) {
        this.id = ship.getId();
        this.owner_id = ship.getOwner().getId();
        this.name = ship.getName();
        this.description = ship.getDescription();
        this.country = ship.getAddress().getCountry();
        this.city = ship.getAddress().getCity();
        this.street = ship.getAddress().getStreet();
        this.pictures = new ArrayList<>(ship.getPictures());
        this.price = ship.getPrice();
        this.rulesOfConduct = new ArrayList<>(ship.getRulesOfConduct());
        this.type = ship.getType();
        this.length = ship.getLength();
        this.engineNum = ship.getEngineNum();
        this.enginePower = ship.getEnginePower();
        this.maxSpeed = ship.getMaxSpeed();
        this.navigationEquipment = new ArrayList<>(ship.getNavigationEquipment());
        this.capacity = ship.getCapacity();
        this.fishingEquipment = new ArrayList<>(ship.getFishingEquipment());
        this.reservationCancellationConditions = ship.getReservationCancellationConditions();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService : ship.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
        this.serviceType = serviceType;
        this.averageRating = averageRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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

    public void setStreet(String address) {
        this.street = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public List<String> getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(List<String> navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(List<String> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getReservationCancellationConditions() {
        return reservationCancellationConditions;
    }

    public void setReservationCancellationConditions(String reservationCancellationConditions) {
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
