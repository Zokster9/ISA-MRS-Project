package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.UserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="fishing_instructors")
public class FishingInstructor extends User {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Set<Adventure> adventures = new HashSet<>();

    public FishingInstructor() {
    }

    public FishingInstructor(Integer id, String email, String password, String name, String surname, Address address,
                             String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints, String registrationReason, Set<Adventure> adventures) {
        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
        this.adventures = adventures;
    }

    public FishingInstructor(UserDTO userDTO, Address a) {
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

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }
}
