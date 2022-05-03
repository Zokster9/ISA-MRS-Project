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

    @Column(name= "isAnswered", nullable = false)
    private boolean isAnswered;

    public RegistrationReasoning(){

    }

    public RegistrationReasoning(User privilegedUser, String registrationReasoning) {
        this.privilegedUser = privilegedUser;
        this.registrationReasoning = registrationReasoning;
        this.isAnswered = false;
    }

    public RegistrationReasoning(Integer id, User privilegedUser, String registrationReasoning, boolean isAnswered){
        this.id = id;
        this.privilegedUser = privilegedUser;
        this.registrationReasoning = registrationReasoning;
        this.isAnswered = isAnswered;
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
