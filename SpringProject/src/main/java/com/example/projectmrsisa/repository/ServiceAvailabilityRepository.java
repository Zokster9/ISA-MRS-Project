package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.ServiceAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceAvailabilityRepository extends JpaRepository<ServiceAvailability, Integer> {

    @Query("select sa from ServiceAvailability sa where sa.service.id = ?1")
    public List<ServiceAvailability> findServiceAvailabilitiesByService(Integer serviceId);
}
