package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

    @Query("select r from Revision r where r.isAnswered=false")
    public List<Revision> findUnansweredRevisions();

    @Query("select r from Revision r where r.id=?1")
    public Revision findRevisionById(Integer id);

    @Modifying
    @Query("update Revision r set r.isAnswered=true where r.id=?1")
    public void updateRevisionAnsweredStatus(Integer id);

    @Modifying
    @Query("update Revision r set r.isApproved=true where r.id=?1")
    public void updateRevisionApprovedStatus(Integer id);
}
