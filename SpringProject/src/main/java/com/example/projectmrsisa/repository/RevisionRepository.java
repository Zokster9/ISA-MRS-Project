package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

    @Query("select r from Revision r where r.isAnswered=false")
    public List<Revision> findUnansweredRevisions();
}
