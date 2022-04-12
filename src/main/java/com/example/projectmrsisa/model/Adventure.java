package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="adventures")
public class Adventure extends Service {
    @Column(name = "instructorBiography", nullable = false)
    private String instructorBiography;
    @Column(name = "maxNumOfPeople", nullable = false)
    private int maxNumOfPeople;
    @ElementCollection
    @CollectionTable(name = "my_fishingEquipment_adventures", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "fishingEquipment", nullable = false)
    private Set<String> fishingEquipment = new HashSet<>();
    @Column(name = "reservationCancellationConditions", nullable = false)
    private String reservationCancellationConditions;

    public Adventure() {
    }

    public Adventure(Integer id, String name, String description, User owner, Address address, Set<String> pictures,
                     Set<String> rulesOfConduct, double price, boolean isDeleted, Set<Action> actions,
                     String instructorBiography, int maxNumOfPeople, Set<String> fishingEquipment,
                     String reservationCancellationConditions) {
        super(id, name, description, owner, address, pictures, rulesOfConduct, price, isDeleted, actions);
        this.instructorBiography = instructorBiography;
        this.maxNumOfPeople = maxNumOfPeople;
        this.fishingEquipment = fishingEquipment;
        this.reservationCancellationConditions = reservationCancellationConditions;
    }

    public String getInstructorBiography() {
        return instructorBiography;
    }

    public void setInstructorBiography(String instructorBiography) {
        this.instructorBiography = instructorBiography;
    }

    public int getMaxNumOfPeople() {
        return maxNumOfPeople;
    }

    public void setMaxNumOfPeople(int maxNumOfPeople) {
        this.maxNumOfPeople = maxNumOfPeople;
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
}
