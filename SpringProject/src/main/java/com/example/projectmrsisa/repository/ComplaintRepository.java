package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query("select c from Complaint c where c.isAnswered=false")
    public List<Complaint> findUnansweredComplaint();
}
