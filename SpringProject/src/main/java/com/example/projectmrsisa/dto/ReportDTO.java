package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Report;

public class ReportDTO {

    private Integer id;
    private String report;
    private boolean isNegative;
    private boolean hasShowedUp;
    private boolean isPenalized;
    private boolean isAnswered;
    private Integer reservationId;

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
}
