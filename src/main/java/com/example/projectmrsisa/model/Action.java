package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actions")
public class Action extends Reservation {
    @Column(name="maxNumOfPeople", nullable = false)
    private int maxNumOfPeople;
    @Column(name="duration", nullable = false)
    private String duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventPlace_id")
    private Address eventPlace;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "action_tags", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "action_id"))
    private Set<Tag> additionalServices = new HashSet<>();

    public Action() {
    }

    public Action(Integer id, Long fromDate, Long toDate, double price, ReservationStatus status, Rating rating,
                  Service service, int maxNumOfPeople, String duration, Address eventPlace, Set<Tag> additionalServices) {
        super(id, fromDate, toDate, price, status, rating, service);
        this.maxNumOfPeople = maxNumOfPeople;
        this.duration = duration;
        this.eventPlace = eventPlace;
        this.additionalServices = additionalServices;
    }

    public int getMaxNumOfPeople() {
        return maxNumOfPeople;
    }

    public void setMaxNumOfPeople(int maxNumOfPeople) {
        this.maxNumOfPeople = maxNumOfPeople;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Address getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(Address eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Set<Tag> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<Tag> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
