package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Report;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("select r from Report r where r.isAnswered=false and r.isNegative=true")
    public List<Report> findNegativeUnansweredReports();

    @Query("select r from Report r where r.id=?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Report findReportById(Integer id);

    @Modifying
    @Query("update Report r set r.isAnswered=true where r.id=?1")
    public void setReportAnswered(Integer id);

    @Modifying
    @Query("update Report r set r.isPenalized=true where r.id=?1")
    public void setReportPenalized(Integer id);
}
