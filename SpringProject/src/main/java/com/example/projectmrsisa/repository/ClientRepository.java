package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Modifying
    @Query("update Client c set c.penaltyPoints = 0 where c.id = ?1")
    void resetPenaltyPoints(int id);

    @Modifying
    @Query("update Client c set c.isPenalized = false where c.id = ?1")
    void resetPenaltyStatus(int id);

    @Modifying
    @Query("update Client c set c.penaltyPoints = c.penaltyPoints + 1 where c.id = ?1")
    void updatePenaltyPoints(int id);

    @Modifying
    @Query("update Client c set c.isPenalized = true where c.id = ?1")
    void updatePenaltyStatus(int id);
}
