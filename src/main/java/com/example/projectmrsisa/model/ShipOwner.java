package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ship_owners")
public class ShipOwner extends User {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ships")
    private Set<Ship> ships = new HashSet<>();

    public ShipOwner() {
    }

    public ShipOwner(Integer id, String email, String password, String name, String surname, Address address,
                     String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints, Set<Ship> ships) {
        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
        this.ships = ships;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }
}
