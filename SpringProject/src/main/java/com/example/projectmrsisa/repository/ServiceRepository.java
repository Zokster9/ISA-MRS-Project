package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query("select s from Service s where s.owner=?1 and s.isDeleted = false")
    public List<Service> findOwnersServices(User owner);

    @Modifying
    @Query("update Service s set s.isDeleted = true where s.id = ?1")
    public void deleteServiceById(Integer id);

    @Modifying
    @Query("update Service s set s.isDeleted = true where s.owner = ?1")
    public void deleteServicesByOwner(User user);

    @Query("select s from Service s where s.id = ?1")
    public Service getServiceById(Integer id);
}
