package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.RevisionDTO;

import javax.persistence.*;

@Entity
@Table(name="revisions")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="revision", nullable = false)
    private String userRevision;
    @Column(name="isApproved")
    private boolean isApproved;
    @Column(name="isAnswered")
    private boolean isAnswered;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id", nullable = false)
    private Rating rating;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Revision() {
        this.isApproved = false;
        this.isAnswered = false;
    }

    public Revision(Integer id, String userRevision, boolean isApproved, boolean isAnswered, Rating rating, Reservation reservation) {
        this.id = id;
        this.userRevision = userRevision;
        this.isApproved = isApproved;
        this.isAnswered = isAnswered;
        this.rating = rating;
        this.reservation = reservation;
    }

    public Revision(RevisionDTO revisionDTO, Rating rating, Reservation reservation) {
        this.userRevision = revisionDTO.getRevision();
        this.isApproved = false;
        this.isAnswered = false;
        this.rating = rating;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRevision() {
        return userRevision;
    }

    public void setUserRevision(String revision) {
        this.userRevision = revision;
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
