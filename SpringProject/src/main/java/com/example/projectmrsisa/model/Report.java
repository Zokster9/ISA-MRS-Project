package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="report", nullable = false)
    private String userReport;
    @Column(name="isNegative")
    private boolean isNegative;
    @Column(name="hasShowedUp")
    private boolean hasShowedUp;
    @Column(name="isPenalized")
    private boolean isPenalized;
    @Column(name="isAnswered")
    private boolean isAnswered;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Report() {
    }

    public Report(Integer id, String userReport, boolean isNegative, boolean hasShowedUp, boolean isPenalized,
                  boolean isAnswered, Reservation reservation) {
        this.id = id;
        this.userReport = userReport;
        this.isNegative = isNegative;
        this.hasShowedUp = hasShowedUp;
        this.isPenalized = isPenalized;
        this.isAnswered = isAnswered;
        this.reservation = reservation;
    }

    public Report(String userReport, boolean isNegative, boolean hasShowedUp, boolean isPenalized,
                  boolean isAnswered, Reservation reservation){
        this.userReport = userReport;
        this.isNegative = isNegative;
        this.hasShowedUp = hasShowedUp;
        this.isPenalized = isPenalized;
        this.isAnswered = isAnswered;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserReport() {
        return userReport;
    }

    public void setUserReport(String report) {
        this.userReport = report;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public boolean isHasShowedUp() {
        return hasShowedUp;
    }

    public void setHasShowedUp(boolean hasShowedUp) {
        this.hasShowedUp = hasShowedUp;
    }

    public boolean isPenalized() {
        return isPenalized;
    }

    public void setPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
