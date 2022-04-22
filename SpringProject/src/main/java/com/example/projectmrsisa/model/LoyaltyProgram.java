package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="loyalty_programs")
public class LoyaltyProgram {
    @Id
    @SequenceGenerator(name = "myLoyProgSeqGen", sequenceName = "myLoyProgSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myLoyProgSeqGen")
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="clientPointsPerReservation", nullable = false)
    private int clientPointsPerReservation;
    @Column(name="privilegedPointsPerReservation", nullable = false)
    private int privilegedPointsPerReservation;
    @ElementCollection
    @JoinTable(name="requiredPoints", joinColumns=@JoinColumn(name="id"))
    @MapKeyColumn (name="points_id")
    @Column(name="value")
    private Map<LoyaltyStatus, Integer> requiredPoints = new HashMap<>();
    @Column(name="silverBonus", nullable = false)
    private double silverBonus;
    @Column(name="goldBonus", nullable = false)
    private double goldBonus;

    public LoyaltyProgram() {
    }

    public LoyaltyProgram(Integer id, int clientPointsPerReservation, int privilegedPointsPerReservation,
                          Map<LoyaltyStatus, Integer> requiredPoints, double silverBonus, double goldBonus) {
        this.id = id;
        this.clientPointsPerReservation = clientPointsPerReservation;
        this.privilegedPointsPerReservation = privilegedPointsPerReservation;
        this.requiredPoints = requiredPoints;
        this.silverBonus = silverBonus;
        this.goldBonus = goldBonus;
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

    public Map<LoyaltyStatus, Integer> getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(Map<LoyaltyStatus, Integer> requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public double getSilverBonus() {
        return silverBonus;
    }

    public void setSilverBonus(double silverBonus) {
        this.silverBonus = silverBonus;
    }

    public double getGoldBonus() {
        return goldBonus;
    }

    public void setGoldBonus(double goldBonus) {
        this.goldBonus = goldBonus;
    }
}
