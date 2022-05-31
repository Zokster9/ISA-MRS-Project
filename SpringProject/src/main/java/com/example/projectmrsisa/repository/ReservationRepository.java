package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.service.id = ?1")
    public List<Reservation> findReservationByServiceId(Integer serviceId);

    @Modifying
    @Query("update Reservation r set r.status=?1 where r.id=?2")
    public void changeReservationStatus(ReservationStatus reservationStatus, Integer id);

    @Query("select r from Reservation r where r.id = ?1")
    public Reservation findReservationById(Integer id);
    
    @Query("select r from Reservation r where r.client.id = ?1 and r.service.id = ?2")
    public List<Reservation> findReservationsForClientAndService(Integer clientId, Integer serviceId);

    @Query("select r from Reservation r where r.client.id = ?1 and r.status = 1")
    List<Reservation> findClientsFinishedReservations(Integer clientId);

    @Query("select r from Reservation r where r.client.id = ?1 and r.status = 0")
    List<Reservation> findClientsPendingReservations(Integer clientId);

    @Query("select r from Reservation r where r.status <> 2")
    List<Reservation> findNonCancelledReservations();

    @Query("select r from Reservation r where r.fromDate >= ?1 and r.fromDate <= ?2 and r.status <> 2")
    List<Reservation> findReservationsInDateSpan(Date fromDate, Date toDate);

    @Query("select r from Reservation r where r.service.owner.id =?1 and r.status <> 2")
    List<Reservation> findPrivilegedUsersReservations(Integer id);

    @Query("select r from Reservation r where r.fromDate >= ?1 and r.fromDate <= ?2 and r.service.owner.id = ?3 and r.status <> 2")
    List<Reservation> findReservationsInDateSpanForPrivilegedUser(Date fromDate, Date toDate, Integer id);
}
