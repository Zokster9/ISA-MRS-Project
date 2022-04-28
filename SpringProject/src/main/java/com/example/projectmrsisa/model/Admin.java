package com.example.projectmrsisa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends User {
//    @Column(name="isPasswordChanged")
//    private boolean isPasswordChanged;
//
//    public Admin(){
//
//    }
//
//    public Admin(Integer id, String email, String password, String name, String surname, Address address,
//                String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints) {
//        super(id, email, password, name, surname, address, phoneNumber, loyaltyStatus, loyaltyPoints);
//        this.isPasswordChanged = false;
//    }
//
//    public boolean isPasswordChanged() {
//        return isPasswordChanged;
//    }
//
//    public void setPasswordChanged(boolean passwordChanged) {
//        isPasswordChanged = passwordChanged;
//    }
}
