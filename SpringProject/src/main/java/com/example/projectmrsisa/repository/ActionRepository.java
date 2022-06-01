package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {
    @Query("select a from Action a where a.service.id = ?1 and a.dateFrom >= ?2")
    List<Action> getActionsByServiceAndCurrentDate(int serviceId, Date currentDate);
}
