package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Revision;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

    @Query("select r from Revision r where r.isAnswered=false")
    public List<Revision> findUnansweredRevisions();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Revision r where r.id=?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Revision findRevisionById(Integer id);

    @Modifying
    @Query("update Revision r set r.isAnswered=true where r.id=?1")
    public void updateRevisionAnsweredStatus(Integer id);

    @Modifying
    @Query("update Revision r set r.isApproved=true where r.id=?1")
    public void updateRevisionApprovedStatus(Integer id);

    @Query("select r from Revision r where r.reservation.service.owner.id = ?1")
    public List<Revision> findRevisionForServiceOwner(Integer serviceOwnerId);

    @Query("select r from Revision r where r.reservation.service.id = ?1")
    List<Revision> findRevisionForService(Integer serviceId);

    @Query("select r from Revision r where r.reservation.id = ?1 and r.reservation.client.id = ?2")
    Revision findClientsRevisionForReservation(int reservationId, int clientId);
}
