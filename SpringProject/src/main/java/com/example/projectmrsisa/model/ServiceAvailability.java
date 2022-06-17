package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="service_availabilities")
public class ServiceAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name="dateFrom", nullable = false)
    private Date dateFrom;

    @Column(name="dateTo", nullable = false)
    private Date dateTo;

    @Column(name="timeFrom", nullable = false)
    private String timeFrom;

    @Column(name="timeTo", nullable = false)
    private String timeTo;

    public ServiceAvailability() {}

    public ServiceAvailability(int id, Service service, Date dateFrom, Date dateTo, String timeFrom, String timeTo) {
        this.id = id;
        this.service = service;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public ServiceAvailability(ServiceAvailabilityDTO serviceAvailabilityDTO, Service service) {
        this.service = service;
        this.dateFrom = serviceAvailabilityDTO.getDateFrom();
        this.dateTo = serviceAvailabilityDTO.getDateTo();
        this.timeFrom = serviceAvailabilityDTO.getTimeFrom();
        this.timeTo = serviceAvailabilityDTO.getTimeTo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}
