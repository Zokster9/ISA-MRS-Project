package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Address;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipDTO {

    private String name;
    private String description;
    private String country;
    private String city;
    private String address;
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

    public ShipDTO() {}

    public ShipDTO(String name, String description, String country, String city, String address, List<String> pictures, List<String> rulesOfConduct, String type, double length, String engineNum, int enginePower, String maxSpeed, List<String> navigationEquipment, int capacity, List<String> fishingEquipment, String reservationCancellationConditions) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
