package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
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
    Complaint findComplaintByReservationId(Integer id);
}
