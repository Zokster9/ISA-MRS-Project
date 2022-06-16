package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface TerminationReasoningRepository extends JpaRepository<TerminationReasoning, Integer> {

    @Query("select tr from TerminationReasoning tr where tr.isAnswered=false")
    public List<TerminationReasoning> findUnansweredTerminationReasonings();

    @Modifying
    @Query("update TerminationReasoning tr set tr.isAnswered = true where tr.user = ?1")
    public void updateTerminationReasoningByAnsweredStatus(User user);
}
