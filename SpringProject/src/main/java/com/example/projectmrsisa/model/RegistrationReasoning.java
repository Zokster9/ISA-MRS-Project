package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="registration_reasoning")
public class RegistrationReasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilegedUser", nullable = false)
    private User privilegedUser;

    @Column(name= "registrationReasoning", nullable = false)
    private String registrationReasoning;

    public RegistrationReasoning(){

    }

    public RegistrationReasoning(User privilegedUser, String registrationReasoning) {
        this.privilegedUser = privilegedUser;
        this.registrationReasoning = registrationReasoning;
    }

    public RegistrationReasoning(Integer id, User privilegedUser, String registrationReasoning){
        this.id = id;
        this.privilegedUser = privilegedUser;
        this.registrationReasoning = registrationReasoning;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getPrivilegedUser() {
        return privilegedUser;
    }

    public void setPrivilegedUser(User privilegedUser) {
        this.privilegedUser = privilegedUser;
    }

    public String getRegistrationReasoning() {
        return registrationReasoning;
    }

    public void setRegistrationReasoning(String registrationReasoning) {
        this.registrationReasoning = registrationReasoning;
    }
}
