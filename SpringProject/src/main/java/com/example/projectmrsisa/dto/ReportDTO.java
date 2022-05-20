package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Report;

import java.util.Set;


public class ReportDTO {

    private Integer id;
    private String report;
    private boolean isNegative;
    private boolean hasShowedUp;
    private boolean isPenalized;
    private boolean isAnswered;
    private Integer reservationId;
    private String clientName;
    private String clientSurname;
    private String ownerName;
    private String ownerSurname;
    private String serviceName;
    private Set<String> servicePictures;

    public ReportDTO(){

    }

    public ReportDTO(Report report){
        this.id = report.getId();
        this.report = report.getReport();
        this.isNegative = report.isNegative();
        this.hasShowedUp = report.isHasShowedUp();
        this.isPenalized = report.isPenalized();
        this.isAnswered = report.isAnswered();
        this.reservationId = report.getReservation().getId();
        this.clientName = report.getReservation().getClient().getName();
        this.clientSurname = report.getReservation().getClient().getSurname();
        this.ownerName = report.getReservation().getService().getOwner().getName();
        this.ownerSurname = report.getReservation().getService().getOwner().getSurname();
        this.serviceName = report.getReservation().getService().getName();
        this.servicePictures = report.getReservation().getService().getPictures();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setIsNegative(boolean negative) {
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

    public void setIsPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public void setPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
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
