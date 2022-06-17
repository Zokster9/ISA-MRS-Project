package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Modifying
    @Query("update Client c set c.penaltyPoints = 0, c.isPenalized = false")
    void updateClientsPenaltyStatus();
}
