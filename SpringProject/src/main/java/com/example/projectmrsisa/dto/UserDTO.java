package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.LoyaltyStatus;
import com.example.projectmrsisa.model.PrivilegedUser;
import com.example.projectmrsisa.model.User;

public class UserDTO {
    private Integer id;
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
    private AddressDTO addressDTO;
    private String phoneNumber;
    private LoyaltyStatus loyaltyStatus;
    private int loyaltyPoints;
    private boolean isDeleted;
    private boolean isActive;
    private String registrationType;
    private String privilegedUserType;
    private String registrationExplanation;
    private RegistrationReasoningDTO registrationReasoningDTO;
    private PrivilegedUser privilegedUser;
    private double averageRating;

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
        privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
    }

    public UserDTO(User user, double averageRating){
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
        privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
        this.averageRating = averageRating;
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
        this.privilegedUser = privilegedUser;
    }

    public UserDTO(User user, PrivilegedUser privilegedUser, RegistrationReasoningDTO registrationReasoningDTO){
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
        this.registrationReasoningDTO = registrationReasoningDTO;
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

    public PrivilegedUser getPrivilegedUser() {
        return privilegedUser;
    }

    public void setPrivilegedUser(PrivilegedUser privilegedUser) {
        this.privilegedUser = privilegedUser;
    }

    public RegistrationReasoningDTO getRegistrationReasoningDTO() {
        return registrationReasoningDTO;
    }

    public void setRegistrationReasoningDTO(RegistrationReasoningDTO registrationReasoningDTO) {
        this.registrationReasoningDTO = registrationReasoningDTO;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getPrivilegedUserType() {
        return privilegedUserType;
    }

    public void setPrivilegedUserType(String privilegedUserType) {
        this.privilegedUserType = privilegedUserType;
    }

    public String getRegistrationExplanation() {
        return registrationExplanation;
    }

    public void setRegistrationExplanation(String registrationExplanation) {
        this.registrationExplanation = registrationExplanation;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
