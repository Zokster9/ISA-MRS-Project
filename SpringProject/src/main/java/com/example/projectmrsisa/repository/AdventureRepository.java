package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {

    @Query("select a from Adventure a where a.id = ?1")
    public Adventure findAdventureById(Integer id);
}
