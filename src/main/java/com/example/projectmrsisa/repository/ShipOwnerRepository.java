package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.ShipOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Integer> {

    @Query("select s from ShipOwner s where s.email = ?1")
    public ShipOwner getShipOwnerByEmail(String email);
}
