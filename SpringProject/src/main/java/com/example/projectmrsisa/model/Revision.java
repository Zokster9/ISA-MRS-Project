package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="revisions")
public class Revision {
    @Id
    @SequenceGenerator(name = "myRevisionSeqGen", sequenceName = "myRevisionSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myRevisionSeqGen")
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="revision", nullable = false)
    private String revision;
    @Column(name="isApproved")
    private boolean isApproved;
    @Column(name="isAnswered")
    private boolean isAnswered;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating", nullable = false)
    private Rating rating;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation", nullable = false)
    private Reservation reservation;

    public Revision() {
    }

    public Revision(Integer id, String revision, boolean isApproved, boolean isAnswered, Rating rating, User privilegedUser, Client client, Reservation reservation) {
        this.id = id;
        this.revision = revision;
        this.isApproved = isApproved;
        this.isAnswered = isAnswered;
        this.rating = rating;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
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
