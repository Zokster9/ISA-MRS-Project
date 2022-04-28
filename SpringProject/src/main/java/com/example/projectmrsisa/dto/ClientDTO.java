package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO extends UserDTO {
    private int penaltyPoints;
    private boolean isPenalized;
    private Set<Reservation> reservations = new HashSet<>();
    private Set<Service> subscriptions = new HashSet<>();

    public ClientDTO(User user) {
        super(user);
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
}
