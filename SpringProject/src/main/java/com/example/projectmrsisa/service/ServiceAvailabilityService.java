package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.ServiceAvailability;
import com.example.projectmrsisa.repository.ServiceAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ServiceAvailabilityService {

    @Autowired
    private ServiceAvailabilityRepository serviceAvailabilityRepository;

    @Transactional(readOnly = false)
    public ServiceAvailability addAvailability(Integer serviceId, ServiceAvailability serviceAvailability) {
        if (checkExistingAvailabilities(serviceId, serviceAvailability.getDateFrom(), serviceAvailability.getDateTo())) {
            serviceAvailability.getService().setNumOfAvailabilities(serviceAvailability.getService().getNumOfAvailabilities() + 1);
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

    public List<ServiceAvailability> getServiceAvailabilitiesForService(Integer id) {
        return serviceAvailabilityRepository.findServiceAvailabilitiesByService(id);
    }

    public boolean isAvailable(Integer serviceId, Date fromDate, Date toDate, String fromTime, String toTime) {
        List<ServiceAvailability> availabilityList = serviceAvailabilityRepository.findServiceAvailabilitiesByService(serviceId);
        if (availabilityList.size() == 0) return true;
        for (ServiceAvailability serviceAvailability : availabilityList) {
            if (serviceAvailability.getDateFrom().compareTo(fromDate) <= 0 && serviceAvailability.getDateTo().compareTo(toDate) >= 0) {
                if (serviceAvailability.getDateFrom().compareTo(fromDate) == 0) {
                    if (serviceAvailability.getTimeFrom().compareTo(fromTime) > 0) continue;
                }
                if (serviceAvailability.getDateTo().compareTo(toDate) == 0) {
                    if (serviceAvailability.getTimeTo().compareTo(toTime) < 0) continue;
                }
                return true;
            }
        }
        return false;
    }
}
