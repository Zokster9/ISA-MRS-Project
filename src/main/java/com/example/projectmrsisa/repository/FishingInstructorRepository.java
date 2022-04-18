package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor, Integer> {

    @Query("select fi from FishingInstructor fi where fi.email = ?1")
    public FishingInstructor findFishingInstructorByEmail(String email);
}
