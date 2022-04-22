package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.UserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ship_owners")
public class ShipOwner extends User {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Set<Ship> ships = new HashSet<>();

    public ShipOwner() {
    }

    public ShipOwner(Integer id, String email, String password, String name, String surname, Address address,
                     String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints, String registrationReason, Set<Ship> ships) {
        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
        this.ships = ships;
    }

    public ShipOwner(UserDTO userDTO, Address a) {
        this.setEmail(userDTO.getEmail());
        this.setPassword(userDTO.getPassword());
        this.setName(userDTO.getName());
        this.setSurname(userDTO.getSurname());
        this.setAddress(a);
        this.setPhoneNumber(userDTO.getPhoneNumber());
        this.setLoyaltyStatus(LoyaltyStatus.Regular);
        this.setLoyaltyPoints(0);
        this.setActive(false);
        this.setDeleted(false);
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }
}
