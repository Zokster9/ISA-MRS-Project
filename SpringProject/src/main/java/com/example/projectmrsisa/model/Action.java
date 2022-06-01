package com.example.projectmrsisa.model;

import com.example.projectmrsisa.dto.ActionDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name="dateFrom", nullable = false)
    private Date dateFrom;

    @Column(name="dateTo", nullable = false)
    private Date dateTo;

    @Column(name="timeFrom", nullable = false)
    private String timeFrom;

    @Column(name="timeTo", nullable = false)
    private String timeTo;

    @Column(name="maxNumOfPeople", nullable = false)
    private int maxNumOfPeople;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "action_tags", joinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> additionalServices = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Action() {
    }

    public Action(Integer id, Date dateFrom, Date dateTo, String timeFrom, String timeTo, int maxNumOfPeople, double price, Set<Tag> additionalServices, Service service) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.maxNumOfPeople = maxNumOfPeople;
        this.price = price;
        this.additionalServices = additionalServices;
        this.service = service;
    }

    public Action(ActionDTO actionDTO, Set<Tag> additionalServices, Service service) {
        this.dateFrom = actionDTO.getDateFrom();
        this.dateTo = actionDTO.getDateTo();
        this.timeFrom = actionDTO.getTimeFrom();
        this.timeTo = actionDTO.getTimeTo();
        this.maxNumOfPeople = actionDTO.getMaxNumOfPeople();
        this.price = actionDTO.getPrice();
        this.additionalServices = additionalServices;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getMaxNumOfPeople() {
        return maxNumOfPeople;
    }

    public void setMaxNumOfPeople(int maxNumOfPeople) {
        this.maxNumOfPeople = maxNumOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Tag> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<Tag> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
