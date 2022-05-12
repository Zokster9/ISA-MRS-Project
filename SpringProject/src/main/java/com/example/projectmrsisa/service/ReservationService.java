package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean checkIfReservationsExistForDate(Integer serviceId, Date fromDate, Date toDate) {
        List<Reservation> reservations = reservationRepository.findReservationByServiceId(serviceId);
        for (Reservation reservation: reservations) {
            if (fromDate.compareTo(reservation.getFromDate()) == 0) {
                return false;
            }else if (fromDate.compareTo(reservation.getFromDate()) < 0) {
                if (toDate.compareTo(reservation.getFromDate()) > 0) return false;
            }else {
                if (fromDate.compareTo(reservation.getToDate()) < 0) return false;
            }
        }
        return true;
    }

    public List<Reservation> findReservationByServiceId(Integer serviceId){
        return reservationRepository.findReservationByServiceId(serviceId);
    }
}
