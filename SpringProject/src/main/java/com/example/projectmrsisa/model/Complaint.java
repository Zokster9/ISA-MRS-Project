package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="complaint", nullable = false)
    private String complaint;
    @Column(name="response")
    private String response;
    @Column(name="isApproved")
    private boolean isApproved;
    @Column(name="isAnswered")
    private boolean isAnswered;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilegedUser", nullable = false)
    private User privilegedUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    public Complaint() {
        this.isApproved = false;
        this.isAnswered = false;
    }

    public Complaint(Integer id, String complaint, String response, boolean isApproved, boolean isAnswered,
                     User privilegedUser, Client client) {
        this.id = id;
        this.complaint = complaint;
        this.response = response;
        this.isApproved = isApproved;
        this.isAnswered = isAnswered;
        this.privilegedUser = privilegedUser;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public User getPrivilegedUser() {
        return privilegedUser;
    }

    public void setPrivilegedUser(User privilegedUser) {
        this.privilegedUser = privilegedUser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
