package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.ReservationDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="fromDate", nullable = false)
    private Date fromDate;
    @Column(name="toDate", nullable = false)
    private Date toDate;
    @Column(name="fromTime", nullable = false)
    private String fromTime;
    @Column(name="toTime", nullable = false)
    private String toTime;
    @Column(name="price", nullable = false)
    private double price;
    @Column(name = "numOfPeople", nullable = false)
    private int numOfPeople;
    @Column(name="status", nullable = false)
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservations_tags", joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> additionalServices = new HashSet<>();

    public Reservation() {
    }

    public Reservation(Integer id, Date fromDate, Date toDate, String fromTime, String toTime, double price, int numOfPeople, ReservationStatus status, Service service, Client client, Set<Tag> additionalServices) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.price = price;
        this.numOfPeople = numOfPeople;
        this.status = status;
        this.service = service;
        this.client = client;
        this.additionalServices = additionalServices;
    }

    public Reservation(ReservationDTO reservationDTO, Service service, Client client, Set<Tag> additionalServices) {
        this.fromDate = reservationDTO.getFromDate();
        this.toDate = reservationDTO.getToDate();
        this.fromTime = reservationDTO.getFromTime();
        this.toTime = reservationDTO.getToTime();
        this.price = reservationDTO.getPrice();
        this.numOfPeople = reservationDTO.getNumOfPeople();
        this.status = ReservationStatus.Pending;
        this.service = service;
        this.client = client;
        this.additionalServices = additionalServices;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Set<Tag> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<Tag> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
