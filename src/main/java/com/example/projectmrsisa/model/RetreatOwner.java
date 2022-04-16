package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.UserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="retreat_owners")
public class RetreatOwner extends User {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Set<Retreat> retreats = new HashSet<>();

    public RetreatOwner() {
    }

    public RetreatOwner(Integer id, String email, String password, String name, String surname, Address address,
                        String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints, String registrationReason, Set<Retreat> retreats) {
        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
        this.retreats = retreats;
    }

    public RetreatOwner(UserDTO userDTO, Address a) {
        this.setEmail(userDTO.getEmail());
        this.setPassword(userDTO.getPassword());
        this.setName(userDTO.getName());
        this.setSurname(userDTO.getSurname());
        this.setAddress(a);
        this.setPhoneNumber(userDTO.getPhoneNumber());
        this.setLoyaltyStatus(LoyaltyStatus.Regular);
        this.setLoyaltyPoints(0);
    }

    public Set<Retreat> getRetreats() {
        return retreats;
    }

    public void setRetreats(Set<Retreat> retreats) {
        this.retreats = retreats;
    }
}
