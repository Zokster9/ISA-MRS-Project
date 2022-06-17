package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.ShipDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ships")
public class Ship extends Service {
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "length", nullable = false)
    private double length;
    @Column(name = "engineNum", nullable = false)
    private String engineNum;
    @Column(name = "enginePower", nullable = false)
    private int enginePower;
    @Column(name = "maxSpeed", nullable = false)
    private String maxSpeed;
    @ElementCollection
    @CollectionTable(name = "my_navigationEquipment", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "navigationEquipment", nullable = false)
    private Set<String> navigationEquipment = new HashSet<>();
    @Column(name = "capacity", nullable = false)
    private int capacity;
    @ElementCollection
    @CollectionTable(name = "my_fishingEquipment_ships", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "fishingEquipment", nullable = false)
    private Set<String> fishingEquipment = new HashSet<>();
    @Column(name = "reservationCancellationConditions", nullable = false)
    private String reservationCancellationConditions;

    public Ship() {
    }

    public Ship(Integer id, String name, String description, User owner, Address address, Set<String> pictures,
                Set<String> rulesOfConduct, double price, boolean isDeleted, Set<Action> actions, String type,
                double length, String engineNum, int enginePower, String maxSpeed, Set<String> navigationEquipment,
                int capacity, Set<String> fishingEquipment, String reservationCancellationConditions, Set<Tag> additionalServices) {
        super(id, name, description, owner, address, pictures, rulesOfConduct, price, isDeleted, actions, additionalServices);
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

    public Ship(ShipDTO shipDTO, Address address, User shipOwner, Set<Tag> additionalServices) {
        this.setName(shipDTO.getName());
        this.setDescription(shipDTO.getDescription());
        this.setOwner(shipOwner);
        this.setAddress(address);
        this.setPictures(new HashSet<>(shipDTO.getPictures()));
        this.setRulesOfConduct(new HashSet<>(shipDTO.getRulesOfConduct()));
        this.setPrice(0);
        this.setDeleted(false);
        this.setActions(new HashSet<>());
        this.type = shipDTO.getType();
        this.length = shipDTO.getLength();
        this.engineNum = shipDTO.getEngineNum();
        this.enginePower = shipDTO.getEnginePower();
        this.maxSpeed = shipDTO.getMaxSpeed();
        this.navigationEquipment = new HashSet<>(shipDTO.getNavigationEquipment());
        this.capacity = shipDTO.getCapacity();
        this.fishingEquipment = new HashSet<>(shipDTO.getFishingEquipment());
        this.reservationCancellationConditions = shipDTO.getReservationCancellationConditions();
        this.setAdditionalServices(additionalServices);
        this.setNumberOfReservations(0);
        this.setNumOfAvailabilities(0);
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

    public Set<String> getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(Set<String> navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<String> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(Set<String> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getReservationCancellationConditions() {
        return reservationCancellationConditions;
    }

    public void setReservationCancellationConditions(String reservationCancellationConditions) {
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public void addFishingEquipment(String fishingEquipment) {
        this.fishingEquipment.add(fishingEquipment);
    }

    public void addNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment.add(navigationEquipment);
    }
}
