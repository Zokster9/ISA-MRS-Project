package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Retreat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface RetreatRepository extends JpaRepository<Retreat, Integer> {

    @Query("select r from Retreat r where r.id = ?1")
    public Retreat getRetreatById(Integer id);

    @Modifying
    @Query("update Retreat r set r.isDeleted = true where r.id = ?1")
    public void deleteRetreatById(Integer id);
}
