package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShipRepository extends JpaRepository<Ship, Integer> {

    @Query("select s from Ship s where s.id = ?1")
    public Ship findShipById(int id);
}
