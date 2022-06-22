package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private User user;

    @Column(name="isPasswordChanged")
    private boolean isPasswordChanged;

    public Admin(){

    }

    public Admin(User user) {
        this.user = user;
        this.isPasswordChanged = false;
    }

    public boolean isPasswordChanged() {
        return isPasswordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        isPasswordChanged = passwordChanged;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
