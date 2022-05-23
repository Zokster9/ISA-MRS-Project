package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.ComplaintDTO;

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
    @Column(name="isAnswered")
    private boolean isAnswered;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Complaint() {
        this.isAnswered = false;
    }

    public Complaint(Integer id, String complaint, String response, boolean isAnswered,
                     Reservation reservation) {
        this.id = id;
        this.complaint = complaint;
        this.response = response;
        this.isAnswered = isAnswered;
        this.reservation = reservation;
    }

    public Complaint(ComplaintDTO complaintDTO, Reservation reservation) {
        this.complaint = complaintDTO.getComplaint();
        this.response = "";
        this.isAnswered = false;
        this.reservation = reservation;
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Reservation getReservation() {return reservation;}

    public void setReservation(Reservation reservation) { this.reservation = reservation;}
}
