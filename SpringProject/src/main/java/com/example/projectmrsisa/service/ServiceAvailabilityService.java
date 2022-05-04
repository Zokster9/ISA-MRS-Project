package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.ServiceAvailability;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAvailabilityService {

    public List<ServiceAvailability> findAvailabilitiesByDates(Long fromDate, Long toDate) {
        return new ArrayList<>();
    }
}
