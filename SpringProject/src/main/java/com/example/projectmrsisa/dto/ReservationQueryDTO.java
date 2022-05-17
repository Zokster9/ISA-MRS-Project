package com.example.projectmrsisa.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationQueryDTO {
    private String serviceType;
    private String fromDate;
    private String fromTime;
    private String toTime;
    private int numOfDays;
    private int numOfPeople;

    public ReservationQueryDTO() {}

    public ReservationQueryDTO(String serviceType, String fromDate, String fromTime, String toTime, int numOfDays, int numOfPeople) {
        this.serviceType = serviceType;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.numOfDays = numOfDays;
        this.numOfPeople = numOfPeople;
    }

    public Date getToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = formatter.format(getFromDate());
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(formatter.parse(startDate));
        } catch(ParseException e){
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_MONTH, this.numOfDays - 1);
        return cal.getTime();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getFromDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(this.fromDate);
        } catch(ParseException e) {
            return new Date();
        }
    }

    public void setFromDate(String fromDate) {
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
