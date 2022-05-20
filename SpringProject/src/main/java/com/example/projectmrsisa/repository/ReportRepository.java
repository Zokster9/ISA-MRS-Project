package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("select r from Report r where r.isAnswered=false and r.isNegative=true")
    public List<Report> findNegativeUnansweredReports();
}
