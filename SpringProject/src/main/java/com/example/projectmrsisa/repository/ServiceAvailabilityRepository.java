package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.ServiceAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAvailabilityRepository extends JpaRepository<ServiceAvailability, Integer> {
}
