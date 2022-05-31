package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Integer> {

    @Modifying
    @Query("update LoyaltyProgram l set l.isActive = false where l.isActive = true")
    public void finishPreviousLoyaltyProgram();

    @Query("select l from LoyaltyProgram l where l.isActive=true")
    public LoyaltyProgram findActiveLoyaltyProgram();
}
