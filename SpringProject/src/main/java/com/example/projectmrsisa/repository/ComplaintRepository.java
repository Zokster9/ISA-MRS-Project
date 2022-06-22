package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Complaint;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query("select c from Complaint c where c.isAnswered=false")
    public List<Complaint> findUnansweredComplaints();

    @Modifying
    @Query("update Complaint c set c.isAnswered=true where c.id = ?1")
    public void updateComplaintStatus(Integer id);

    @Modifying
    @Query("update Complaint c set c.response=?1 where c.id = ?2")
    public void updateComplaintResponse(String response, Integer id);

    @Query("select c from Complaint c where c.id=?1")
    public Complaint findComplaintById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Complaint c where c.reservation.id = ?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Complaint findComplaintByReservationId(Integer id);
}
