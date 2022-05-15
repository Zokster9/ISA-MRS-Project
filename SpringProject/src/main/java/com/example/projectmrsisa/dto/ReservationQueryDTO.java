package com.example.projectmrsisa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReservationQueryDTO {
    private String serviceType;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date fromDate;
    private String fromTime;
    private String toTime;
    private int numOfDays;
    private int numOfPeople;

    public ReservationQueryDTO() {}

    public ReservationQueryDTO(String serviceType, Date fromDate, String fromTime, String toTime, int numOfDays, int numOfPeople) {
        this.serviceType = serviceType;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.numOfDays = numOfDays;
        this.numOfPeople = numOfPeople;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
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

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
}
