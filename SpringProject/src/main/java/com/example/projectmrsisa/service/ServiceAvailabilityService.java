package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.model.ServiceAvailability;
import com.example.projectmrsisa.repository.ServiceAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceAvailabilityService {

    @Autowired
    private ServiceAvailabilityRepository serviceAvailabilityRepository;

    public ServiceAvailability addAvailability(Integer serviceId, ServiceAvailability serviceAvailability) {
        if (checkExistingAvailabilities(serviceId, serviceAvailability.getDateFrom(), serviceAvailability.getDateTo())) {
            return serviceAvailabilityRepository.save(serviceAvailability);
        }else return null;
    }

    private boolean checkExistingAvailabilities(Integer serviceId, Date fromDate, Date toDate) {
        List<ServiceAvailability> availabilityList = serviceAvailabilityRepository.findServiceAvailabilitiesByService(serviceId);
        for (ServiceAvailability serviceAvailability : availabilityList) {
            if (serviceAvailability.getDateFrom().compareTo(fromDate) == 0) {
                return false;
            }else if (serviceAvailability.getDateFrom().compareTo(fromDate) < 0) {
                if (serviceAvailability.getDateTo().compareTo(fromDate) > 0) return false;
            }else {
                if (serviceAvailability.getDateFrom().compareTo(toDate) < 0) return false;
            }
        }
        return true;
    }
}