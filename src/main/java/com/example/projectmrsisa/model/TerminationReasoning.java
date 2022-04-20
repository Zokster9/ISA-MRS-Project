package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="termination_reasoning")
public class TerminationReasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userOfTermination", nullable = false)
    private User user;

    @Column(name = "terminationReasoning", nullable = false)
    private String terminationReasoning;

    @Column(name = "isAnswered", nullable = false)
    private Boolean isAnswered;

    public TerminationReasoning(){

    }

    public TerminationReasoning(Integer id, User user, String terminationReasoning){
        this.id = id;
        this.user = user;
        this.terminationReasoning = terminationReasoning;
        this.isAnswered = false;
    }

    public TerminationReasoning(User user,String terminationReasoning){
        this.user = user;
        this.terminationReasoning = terminationReasoning;
        this.isAnswered = false;
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

    public String getTerminationReasoning() {
        return terminationReasoning;
    }

    public void setTerminationReasoning(String terminationReasoning) {
        this.terminationReasoning = terminationReasoning;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }
}