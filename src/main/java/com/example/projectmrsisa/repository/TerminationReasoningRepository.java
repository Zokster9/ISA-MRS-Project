package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.TerminationReasoning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TerminationReasoningRepository extends JpaRepository<TerminationReasoning, Integer> {

    @Query("select tr from TerminationReasoning tr where tr.isAnswered=false")
    public List<TerminationReasoning> findUnansweredTerminationReasonings();
}
