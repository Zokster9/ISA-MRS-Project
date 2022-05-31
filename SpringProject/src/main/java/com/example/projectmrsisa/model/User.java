package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.UserDTO;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="email", unique = true, nullable = false)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="surname", nullable = false)
    private String surname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name="loyaltyStatus", nullable = false)
    private LoyaltyStatus loyaltyStatus;
    @Column(name="loyaltyPoints", nullable = false)
    private int loyaltyPoints;
    @Column(name="isDeleted", nullable = false)
    private boolean isDeleted;
    @Column(name="isActive", nullable = false)
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
        this.loyaltyStatus = LoyaltyStatus.Regular;
        this.isDeleted = false;
        this.isActive = false;
    }

    public User(Integer id, String email, String password, String name, String surname, Address address,
                String phoneNumber, LoyaltyStatus loyaltyStatus, int loyaltyPoints) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.loyaltyStatus = loyaltyStatus;
        this.loyaltyPoints = loyaltyPoints;
        this.isDeleted = false;
        this.isActive = false;
    }

    public User(UserDTO userDTO, Address address, Role role) {
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.address = address;
        this.phoneNumber = userDTO.getPhoneNumber();
        this.loyaltyPoints = 0;
        this.loyaltyStatus = LoyaltyStatus.Regular;
        this.isDeleted = false;
        this.isActive = false;
        this.roles = new ArrayList<>();
        this.roles.add(role);
    }

    public User(UserDTO userDTO, Address address, Role role, boolean isAdmin) {
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.name = userDTO.getName();
        this.surname = userDTO.getSurname();
        this.address = address;
        this.phoneNumber = userDTO.getPhoneNumber();
        this.loyaltyPoints = 0;
        this.loyaltyStatus = LoyaltyStatus.Regular;
        this.isDeleted = false;
        this.isActive = isAdmin;
        this.roles = new ArrayList<>();
        this.roles.add(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LoyaltyStatus getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(LoyaltyStatus loyaltyStatus) {
        this.loyaltyStatus = loyaltyStatus;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addPoints(double points){
        this.loyaltyPoints += points;
    }
}
