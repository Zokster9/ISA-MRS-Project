package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.LoyaltyProgram;

public class LoyaltyProgramDTO {
    private int id;
    private int clientPointsPerReservation;
    private int privilegedUserPointsPerReservation;
    private int requiredPointsSilver;
    private int requiredPointsGold;
    private double clientBonusSilver;
    private double privilegedUserBonusSilver;
    private double clientBonusGold;
    private double privilegedUserBonusGold;
    private boolean isActive;

    public LoyaltyProgramDTO(){

    }

    public LoyaltyProgramDTO(LoyaltyProgram loyaltyProgram){
        this.id = loyaltyProgram.getId();
        this.clientPointsPerReservation = loyaltyProgram.getClientPointsPerReservation();
        this.privilegedUserPointsPerReservation = loyaltyProgram.getPrivilegedPointsPerReservation();
        this.requiredPointsSilver = loyaltyProgram.getSilverPointsRequired();
        this.requiredPointsGold = loyaltyProgram.getGoldPointsRequired();
        this.clientBonusSilver = loyaltyProgram.getSilverClientBonus();
        this.privilegedUserBonusSilver = loyaltyProgram.getSilverPrivilegedBonus();
        this.clientBonusGold = loyaltyProgram.getGoldClientBonus();
        this.privilegedUserBonusGold = loyaltyProgram.getGoldPrivilegedUserBonus();
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientPointsPerReservation() {
        return clientPointsPerReservation;
    }

    public void setClientPointsPerReservation(int clientPointsPerReservation) {
        this.clientPointsPerReservation = clientPointsPerReservation;
    }

    public int getPrivilegedUserPointsPerReservation() {
        return privilegedUserPointsPerReservation;
    }

    public void setPrivilegedUserPointsPerReservation(int privilegedUserPointsPerReservation) {
        this.privilegedUserPointsPerReservation = privilegedUserPointsPerReservation;
    }

    public int getRequiredPointsSilver() {
        return requiredPointsSilver;
    }

    public void setRequiredPointsSilver(int requiredPointsSilver) {
        this.requiredPointsSilver = requiredPointsSilver;
    }

    public int getRequiredPointsGold() {
        return requiredPointsGold;
    }

    public void setRequiredPointsGold(int requiredPointsGold) {
        this.requiredPointsGold = requiredPointsGold;
    }

    public double getClientBonusSilver() {
        return clientBonusSilver;
    }

    public void setClientBonusSilver(double clientBonusSilver) {
        this.clientBonusSilver = clientBonusSilver;
    }

    public double getPrivilegedUserBonusSilver() {
        return privilegedUserBonusSilver;
    }

    public void setPrivilegedUserBonusSilver(double privilegedUserBonusSilver) {
        this.privilegedUserBonusSilver = privilegedUserBonusSilver;
    }

    public double getClientBonusGold() {
        return clientBonusGold;
    }

    public void setClientBonusGold(double clientBonusGold) {
        this.clientBonusGold = clientBonusGold;
    }

    public double getPrivilegedUserBonusGold() {
        return privilegedUserBonusGold;
    }

    public void setPrivilegedUserBonusGold(double privilegedUserBonusGold) {
        this.privilegedUserBonusGold = privilegedUserBonusGold;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
