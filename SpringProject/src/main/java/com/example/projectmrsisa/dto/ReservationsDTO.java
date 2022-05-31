package com.example.projectmrsisa.dto;

import java.util.List;

public class ReservationsDTO {

    private List<ReservationDTO> reservationsDTO;

    public ReservationsDTO(){

    }

    public ReservationsDTO(List<ReservationDTO> reservationDTOS){
        this.reservationsDTO = reservationsDTO;
    }

    public List<ReservationDTO> getReservationsDTO() {
        return reservationsDTO;
    }

    public void setReservationsDTO(List<ReservationDTO> reservationsDTO) {
        this.reservationsDTO = reservationsDTO;
    }
}
