package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.FishingInstructor;
import com.example.projectmrsisa.model.LoyaltyStatus;

public class FishingInstructorDTO {

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String street;
    private String phoneNumber;
    private LoyaltyStatus loyaltyStatus;
    private int loyaltyPoints;
    private boolean isDeleted;
    private boolean isActive;
    //TODO: Dodati koje su avanture njegove!
    //private List<>

    public FishingInstructorDTO(){

    }

    public FishingInstructorDTO(FishingInstructor fishingInstructor){
        id = fishingInstructor.getId();
        email = fishingInstructor.getEmail();
        password = fishingInstructor.getPassword();
        name = fishingInstructor.getName();
        surname = fishingInstructor.getSurname();
        country =  fishingInstructor.getAddress().getCountry();
        city = fishingInstructor.getAddress().getCity();
        street = fishingInstructor.getAddress().getStreet();
        phoneNumber = fishingInstructor.getPhoneNumber();
        loyaltyStatus = fishingInstructor.getLoyaltyStatus();
        loyaltyPoints = fishingInstructor.getLoyaltyPoints();
        isDeleted = fishingInstructor.isDeleted();
        isActive = fishingInstructor.isActive();
        ///TODO
        // List<>
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(LoyaltyStatus loyaltyStatus) {
        this.loyaltyStatus = loyaltyStatus;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
}
