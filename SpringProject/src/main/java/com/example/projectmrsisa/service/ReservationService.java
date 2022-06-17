package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ReservationDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import com.example.projectmrsisa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(readOnly = false)
    public Reservation addReservation(Reservation reservation) {
        reservation.getService().setNumberOfReservations(reservation.getService().getNumberOfReservations() + 1);
        return reservationRepository.save(reservation);
    }

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

    public List<Reservation> findClientsFinishedReservations(Integer clientId) {
        return reservationRepository.findClientsFinishedReservations(clientId);
    }

    public List<Reservation> findClientsPendingReservations(Integer clientId) {
        return reservationRepository.findClientsPendingReservations(clientId);
    }

    public List<Reservation> findReservationByServiceId(Integer serviceId) {
        return reservationRepository.findReservationByServiceId(serviceId);
    }

    public Reservation findReservationById(Integer reservationId) {
        return reservationRepository.findReservationById(reservationId);
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
                        return (reservation.getStatus() == ReservationStatus.Cancelled || reservation.getStatus() == ReservationStatus.Pending)
                                && Objects.equals(reservation.getClient().getId(), clientId);
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
    
    public boolean currentReservationForClientAndService(Integer serviceId, Client client) {
        List<Reservation> reservations = reservationRepository.findReservationsForClientAndService(client.getId(), serviceId);
        Date today = new Date();
        for (Reservation reservation: reservations) {
            if (reservation.getFromDate().compareTo(today) < 0 && reservation.getToDate().compareTo(today) > 0) return true;
            else if (reservation.getFromDate().compareTo(today) == 0) return true;
            else if (reservation.getToDate().compareTo(today) == 0) return true;
        }
        return false;
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public List<Reservation> findNonCancelledReservations(){
        return reservationRepository.findNonCancelledReservations();
    }

    public List<Reservation> findReservationsInDateSpan(Date fromDate, Date toDate){
        return reservationRepository.findReservationsInDateSpan(fromDate, toDate);
    }

    public List<Reservation> findPrivilegedUsersReservations(Integer id){
        return reservationRepository.findPrivilegedUsersReservations(id);
    }

    public List<Reservation> findReservationsInDateSpanForPrivilegedUser(Date fromDate, Date toDate, Integer id){
        return reservationRepository.findReservationsInDateSpanForPrivilegedUser(fromDate, toDate, id);
    }

    public boolean pendingReservationForServiceExists(Integer serviceId) {
        List<Reservation> reservations = reservationRepository.findReservationByServiceId(serviceId);
        for (Reservation reservation: reservations) {
            if (reservation.getStatus() == ReservationStatus.Pending) return false;
        }
        return true;
    }
}
