package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionDTO {

    private Integer id;

    private Date dateFrom;

    private Date dateTo;

    private String timeFrom;

    private String timeTo;

    private int maxNumOfPeople;

    private double price;

    private List<String> additionalServices;

    public ActionDTO() {
        additionalServices = new ArrayList<>();
    }

    public ActionDTO(Integer id, Date dateFrom, Date dateTo, String timeFrom, String timeTo, int maxNumOfPeople, double price, List<String> additionalServices) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.maxNumOfPeople = maxNumOfPeople;
        this.price = price;
        this.additionalServices = additionalServices;
    }

    public ActionDTO(Action action) {
        this.id = action.getId();
        this.dateFrom = action.getDateFrom();
        this.dateTo = action.getDateTo();
        this.timeFrom = action.getTimeFrom();
        this.timeTo = action.getTimeTo();
        this.maxNumOfPeople = action.getMaxNumOfPeople();
        this.price = action.getPrice();
        this.additionalServices = new ArrayList<>();
        for (Tag additionalService: action.getAdditionalServices()) {
            this.additionalServices.add(additionalService.getName());
        }
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

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
