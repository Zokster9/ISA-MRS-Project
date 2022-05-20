package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.UserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="clients")
public class Client extends User {
    @Column(name = "penaltyPoints")
    private int penaltyPoints;
    @Column(name = "isPenalized")
    private boolean isPenalized;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Set<Reservation> reservations = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"))
    private Set<Service> subscriptions = new HashSet<>();

    public Client() {
        this.isPenalized = false;
    }

    public Client(Integer id, String email, String password, String name, String surname, Address address,
                  String phoneNumber, LoyaltyStatus loyaltyStatus, String registrationReason, int loyaltyPoints, int penaltyPoints,
                  boolean isPenalized, Set<Reservation> reservations, Set<Service> subscriptions) {
        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
        this.penaltyPoints = penaltyPoints;
        this.isPenalized = isPenalized;
        this.reservations = reservations;
        this.subscriptions = subscriptions;
    }

    public Client(UserDTO userDTO, Address address, Role role) {
        super(userDTO, address, role);
        this.penaltyPoints = 0;
        this.isPenalized = false;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public boolean isPenalized() {
        return isPenalized;
    }

    public void setPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Service> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Service> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean hasSubscription(Integer serviceId) {
        for (Service service: this.subscriptions) {
            if (service.getId() == serviceId) return true;
        }
        return false;
    }

    public void penalize(){
        this.penaltyPoints += 1;
    }
}
