package com.example.projectmrsisa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservations")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation {
    @Id
    @SequenceGenerator(name = "myReservationSeqGen", sequenceName = "myReservationSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myReservationSeqGen")
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="fromDate", unique = true, nullable = false)
    private Date fromDate;
    @Column(name="toDate", unique = true, nullable = false)
    private Date toDate;
    @Column(name="price", unique = true, nullable = false)
    private double price;
    @Column(name="status", unique = true, nullable = false)
    private ReservationStatus status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id")
    private Rating rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Reservation() {
    }

    public Reservation(Integer id, Date fromDate, Date toDate, double price, ReservationStatus status, Rating rating,
                       Service service) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.status = status;
        this.rating = rating;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
