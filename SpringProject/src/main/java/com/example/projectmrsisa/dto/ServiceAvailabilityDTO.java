package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.ServiceAvailability;

import java.util.Date;

public class ServiceAvailabilityDTO {

    private int serviceId;

    private Date dateFrom;

    private Date dateTo;

    private String timeFrom;

    private String timeTo;

    public ServiceAvailabilityDTO() {}

    public ServiceAvailabilityDTO(int serviceId, Date dateFrom, Date dateTo, String timeFrom, String timeTo) {
        this.serviceId = serviceId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public ServiceAvailabilityDTO(ServiceAvailability serviceAvailability) {
        this.serviceId = serviceAvailability.getService().getId();
        this.dateFrom = serviceAvailability.getDateFrom();
        this.dateTo = serviceAvailability.getDateTo();
        this.timeFrom = serviceAvailability.getTimeFrom();
        this.timeTo = serviceAvailability.getTimeTo();
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
