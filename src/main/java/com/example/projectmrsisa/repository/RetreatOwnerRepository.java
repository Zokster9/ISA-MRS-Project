package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.RetreatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetreatOwnerRepository extends JpaRepository<RetreatOwner, Integer> {

    @Query("select r from RetreatOwner r where r.email = ?1")
    public RetreatOwner getRetreatOwnerByEmail(String email);
}
