package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import com.example.projectmrsisa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation reservation) { return reservationRepository.save(reservation); }

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

    public List<Reservation> findReservationByServiceId(Integer serviceId) {
        return reservationRepository.findReservationByServiceId(serviceId);
    }

    public boolean isReserved(Integer serviceId, Date fromDate, Date toDate, String fromTime, String toTime, Integer clientId) {
        List<Reservation> reservations = reservationRepository.findReservationByServiceId(serviceId);
        if (reservations.isEmpty()) return false;
        else {
            for (Reservation reservation: reservations) {
                if ((reservation.getFromDate().compareTo(fromDate) <= 0 && reservation.getToDate().compareTo(toDate) >= 0
                        && reservation.getFromDate().compareTo(toDate) <= 0 && reservation.getToDate().compareTo(fromDate) >= 0)
                        || (reservation.getFromDate().compareTo(fromDate) >= 0 && reservation.getToDate().compareTo(toDate) >= 0
                        && reservation.getFromDate().compareTo(toDate) <= 0 && reservation.getToDate().compareTo(fromDate) >= 0)
                        || (reservation.getFromDate().compareTo(fromDate) <= 0 && reservation.getToDate().compareTo(toDate) <= 0
                        && reservation.getFromDate().compareTo(toDate) <= 0 && reservation.getToDate().compareTo(fromDate) >= 0)
                        || (reservation.getFromDate().compareTo(fromDate) >= 0 && reservation.getToDate().compareTo(toDate) <= 0
                        && reservation.getFromDate().compareTo(toDate) <= 0 && reservation.getToDate().compareTo(fromDate) >= 0)) {
                    if (reservation.getFromDate().compareTo(fromDate) == 0 && reservation.getToDate().compareTo(toDate) == 0
                            && reservation.getFromTime().compareTo(fromTime) == 0 && reservation.getToTime().compareTo(toTime) == 0) {
                        return reservation.getStatus() == ReservationStatus.Cancelled && Objects.equals(reservation.getClient().getId(), clientId);
                    }
                    return true;
                }
                else {
                    if (reservation.getToDate().compareTo(fromDate) == 0) {
                        if (reservation.getToTime().compareTo(fromTime) > 0) return true;
                    }
                    if (reservation.getFromDate().compareTo(toDate) == 0) {
                        if (reservation.getFromTime().compareTo(toTime) < 0) return true;
                    }
                }
            }
        }
        return false;
    }

    public void changeReservationStatus(ReservationStatus reservationStatus, Integer id) {
        reservationRepository.changeReservationStatus(reservationStatus, id);
    }

    public Reservation findReservationById(Integer id){
        return reservationRepository.findReservationById(id);
    }
}
