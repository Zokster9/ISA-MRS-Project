package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {

    @Query("select a from Adventure a where a.id = ?1")
    public Adventure findAdventureById(Integer id);

    @Modifying
    @Query("update Adventure a set a.isDeleted = true where a.id = ?1")
    public void deleteAdventureById(Integer id);

    @Query("select a from Adventure a where a.owner.id = ?1")
    public List<Adventure> findOwnersAdventures(Integer ownerId);
}
