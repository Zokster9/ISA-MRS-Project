package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.LoyaltyStatus;
import com.example.projectmrsisa.model.PrivilegedUser;
import com.example.projectmrsisa.model.User;

import javax.persistence.*;

public class UserDTO {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private AddressDTO addressDTO;
    private String phoneNumber;
    private LoyaltyStatus loyaltyStatus;
    private int loyaltyPoints;
    private boolean isDeleted;
    private boolean isActive;
    private String registrationReason;
    private PrivilegedUser privilegedUser;

    public UserDTO(){

    }

    public UserDTO(User user){
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        addressDTO = new AddressDTO(user.getAddress());
        phoneNumber = user.getPhoneNumber();
        loyaltyStatus = user.getLoyaltyStatus();
        loyaltyPoints = user.getLoyaltyPoints();
        isDeleted = user.isDeleted();
        isActive = user.isActive();
        registrationReason = user.getRegistrationReason();
        privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
    }

    public UserDTO(User user, PrivilegedUser privilegedUser){
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        addressDTO = new AddressDTO(user.getAddress());
        phoneNumber = user.getPhoneNumber();
        loyaltyStatus = user.getLoyaltyStatus();
        loyaltyPoints = user.getLoyaltyPoints();
        isDeleted = user.isDeleted();
        isActive = user.isActive();
        registrationReason = user.getRegistrationReason();
        this.privilegedUser = privilegedUser;
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

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
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

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }

    public PrivilegedUser getPrivilegedUser() {
        return privilegedUser;
    }

    public void setPrivilegedUser(PrivilegedUser privilegedUser) {
        this.privilegedUser = privilegedUser;
    }
}
