package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query("select s from Service s where s.owner=?1 and s.isDeleted = false")
    public List<Service> findOwnersServices(User owner);
}
