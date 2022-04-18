package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="services")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="description", nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    @ElementCollection
    @CollectionTable(name = "my_pictures", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "pictures", nullable = false)
    private Set<String> pictures = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "my_rulesOfConduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "rulesOfConduct", nullable = false)
    private Set<String> rulesOfConduct = new HashSet<>();
    @Column(name="price", nullable = false)
    private double price;
    @Column(name="isDeleted", nullable = false)
    private boolean isDeleted;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "service")
    private Set<Action> actions = new HashSet<>();

    public Service() {
        this.isDeleted = false;
    }

    public Service(Integer id, String name, String description, User owner, Address address, Set<String> pictures,
                   Set<String> rulesOfConduct, double price, boolean isDeleted, Set<Action> actions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.address = address;
        this.pictures = pictures;
        this.rulesOfConduct = rulesOfConduct;
        this.price = price;
        this.isDeleted = isDeleted;
        this.actions = actions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<String> getPictures() {
        return pictures;
    }

    public void setPictures(Set<String> pictures) {
        this.pictures = pictures;
    }

    public Set<String> getRulesOfConduct() {
        return rulesOfConduct;
    }

    public void setRulesOfConduct(Set<String> rulesOfConduct) {
        this.rulesOfConduct = rulesOfConduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
