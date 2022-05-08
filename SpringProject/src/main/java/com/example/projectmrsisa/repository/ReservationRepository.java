package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.service.id = ?1")
    public List<Reservation> findReservationByServiceId(Integer serviceId);
}
