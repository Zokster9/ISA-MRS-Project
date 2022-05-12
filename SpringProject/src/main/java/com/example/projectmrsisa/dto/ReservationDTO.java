package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

public class ReservationDTO {
    private Integer id;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date fromDate;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date toDate;
    private String fromTime;
    private String toTime;
    private double price;
    private ReservationStatus status;
    private double userRating;
    private double serviceRating;
    private Integer serviceId;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String clientCountry;
    private String clientCity;
    private String clientStreet;
    private String serviceName;
    private Set<String> servicePictures;

    public ReservationDTO(){

    }

    public ReservationDTO(Reservation reservation){
        this.id = reservation.getId();
        this.fromDate = reservation.getFromDate();
        this.toDate = reservation.getToDate();
        this.fromTime = reservation.getFromTime();
        this.toTime = reservation.getToTime();
        this.price = reservation.getPrice();
        this.status = reservation.getStatus();
        this.userRating = reservation.getRating().getUserRating();
        this.serviceRating = reservation.getRating().getServiceRating();
        this.serviceId = reservation.getService().getId();
        this.clientName = reservation.getClient().getName();
        this.clientSurname = reservation.getClient().getSurname();
        this.clientEmail = reservation.getClient().getEmail();
        this.clientCountry = reservation.getClient().getAddress().getCountry();
        this.clientCity = reservation.getClient().getAddress().getCity();
        this.clientStreet = reservation.getClient().getAddress().getStreet();
        this.serviceName = reservation.getService().getName();
        this.servicePictures = reservation.getService().getPictures();
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

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientStreet() {
        return clientStreet;
    }

    public void setClientStreet(String clientStreet) {
        this.clientStreet = clientStreet;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<String> getServicePictures() {
        return servicePictures;
    }

    public void setServicePictures(Set<String> servicePictures) {
        this.servicePictures = servicePictures;
    }
}
