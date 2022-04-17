package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.RetreatDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="retreats")
public class Retreat extends Service {
    @Column(name="numOfRooms", nullable = false)
    private int numOfRooms;
    @Column(name="numOfBeds", nullable = false)
    private int numOfBeds;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "retreat_tags", joinColumns = @JoinColumn(name = "retreat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> additionalServices = new HashSet<>();

    public Retreat() {
    }

    public Retreat(Integer id, String name, String description, User owner, Address address, Set<String> pictures,
                   Set<String> rulesOfConduct, double price, boolean isDeleted, Set<Action> actions, int numOfRooms,
                   int numOfBeds, Set<Tag> additionalServices) {
        super(id, name, description, owner, address, pictures, rulesOfConduct, price, isDeleted, actions);
        this.numOfRooms = numOfRooms;
        this.numOfBeds = numOfBeds;
        this.additionalServices = additionalServices;
    }

    public Retreat(RetreatDTO retreatDTO, Address address, Set<Tag> additionalServices, RetreatOwner owner) {
        this.setName(retreatDTO.getName());
        this.setDescription(retreatDTO.getDescription());
        this.setOwner(owner);
        this.setAddress(address);
        this.setPictures(new HashSet<>(retreatDTO.getPictures()));
        this.setRulesOfConduct(new HashSet<>(retreatDTO.getRulesOfConduct()));
        this.setPrice(0);
        this.setDeleted(false);
        this.setActions(new HashSet<>());
        this.numOfRooms = retreatDTO.getNumOfRooms();
        this.numOfBeds = retreatDTO.getNumOfBeds();
        this.setAdditionalServices(additionalServices);
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public Set<Tag> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<Tag> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
