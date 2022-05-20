package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.service.id = ?1")
    public List<Reservation> findReservationByServiceId(Integer serviceId);

    @Modifying
    @Query("update Reservation r set r.status=?1 where r.id=?2")
    public void changeReservationStatus(ReservationStatus reservationStatus, Integer id);
}
