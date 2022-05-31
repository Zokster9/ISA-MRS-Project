package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.LoyaltyProgramDTO;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="loyalty_programs")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="clientPointsPerReservation", nullable = false)
    private int clientPointsPerReservation;
    @Column(name="privilegedPointsPerReservation", nullable = false)
    private int privilegedPointsPerReservation;
    @Column(name="silverPointsRequired", nullable = false)
    private int silverPointsRequired;
    @Column(name="goldPointsRequired", nullable = false)
    private int goldPointsRequired;
    @Column(name="silverClientBonus", nullable = false)
    private double silverClientBonus;
    @Column(name="goldClientBonus", nullable = false)
    private double goldClientBonus;
    @Column(name="silverPrivilegedUserBonus", nullable = false)
    private double silverPrivilegedBonus;
    @Column(name="goldPrivilegedUserBonus", nullable = false)
    private double goldPrivilegedUserBonus;
    @Column(name="isActive", nullable = false)
    private boolean isActive;

    public LoyaltyProgram() {
    }

    public LoyaltyProgram(Integer id, int clientPointsPerReservation, int privilegedPointsPerReservation, int silverPointsRequired, int goldPointsRequired,
                          double silverClientBonus, double goldClientBonus, double silverPrivilegedBonus, double goldPrivilegedUserBonus, boolean isActive) {
        this.id = id;
        this.clientPointsPerReservation = clientPointsPerReservation;
        this.privilegedPointsPerReservation = privilegedPointsPerReservation;
        this.silverPointsRequired = silverPointsRequired;
        this.goldPointsRequired = goldPointsRequired;
        this.silverClientBonus = silverClientBonus;
        this.goldClientBonus = goldClientBonus;
        this.silverPrivilegedBonus = silverPrivilegedBonus;
        this.goldPrivilegedUserBonus = goldPrivilegedUserBonus;
        this.isActive = isActive;
    }

    public LoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO){
        this.id = loyaltyProgramDTO.getId();
        this.clientPointsPerReservation = loyaltyProgramDTO.getClientPointsPerReservation();
        this.privilegedPointsPerReservation = loyaltyProgramDTO.getClientPointsPerReservation();
        this.silverPointsRequired = loyaltyProgramDTO.getRequiredPointsSilver();
        this.goldPointsRequired = loyaltyProgramDTO.getRequiredPointsGold();
        this.silverClientBonus = loyaltyProgramDTO.getClientBonusSilver();
        this.goldClientBonus = loyaltyProgramDTO.getClientBonusGold();
        this.silverPrivilegedBonus = loyaltyProgramDTO.getPrivilegedUserBonusSilver();
        this.goldPrivilegedUserBonus = loyaltyProgramDTO.getPrivilegedUserBonusGold();
        this.isActive = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClientPointsPerReservation() {
        return clientPointsPerReservation;
    }

    public void setClientPointsPerReservation(int clientPointsPerReservation) {
        this.clientPointsPerReservation = clientPointsPerReservation;
    }

    public int getPrivilegedPointsPerReservation() {
        return privilegedPointsPerReservation;
    }

    public void setPrivilegedPointsPerReservation(int privilegedPointsPerReservation) {
        this.privilegedPointsPerReservation = privilegedPointsPerReservation;
    }

    public int getSilverPointsRequired() {
        return silverPointsRequired;
    }

    public void setSilverPointsRequired(int silverPointsRequired) {
        this.silverPointsRequired = silverPointsRequired;
    }

    public int getGoldPointsRequired() {
        return goldPointsRequired;
    }

    public void setGoldPointsRequired(int goldPointsRequired) {
        this.goldPointsRequired = goldPointsRequired;
    }

    public double getSilverClientBonus() {
        return silverClientBonus;
    }

    public void setSilverClientBonus(double silverClientBonus) {
        this.silverClientBonus = silverClientBonus;
    }

    public double getGoldClientBonus() {
        return goldClientBonus;
    }

    public void setGoldClientBonus(double goldClientBonus) {
        this.goldClientBonus = goldClientBonus;
    }

    public double getSilverPrivilegedBonus() {
        return silverPrivilegedBonus;
    }

    public void setSilverPrivilegedBonus(double silverPrivilegedBonus) {
        this.silverPrivilegedBonus = silverPrivilegedBonus;
    }

    public double getGoldPrivilegedUserBonus() {
        return goldPrivilegedUserBonus;
    }

    public void setGoldPrivilegedUserBonus(double goldPrivilegedUserBonus) {
        this.goldPrivilegedUserBonus = goldPrivilegedUserBonus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
