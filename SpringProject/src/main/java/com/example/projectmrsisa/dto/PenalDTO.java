package com.example.projectmrsisa.dto;

public class PenalDTO {

    private Integer reservationId;
    private String comment;

    public PenalDTO(){

    }

    public PenalDTO(Integer reservationId, String comment){
        this.reservationId = reservationId;
        this.comment = comment;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
