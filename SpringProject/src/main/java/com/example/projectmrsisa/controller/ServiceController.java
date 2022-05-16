package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.ServiceAvailability;
import com.example.projectmrsisa.service.ActionService;
import com.example.projectmrsisa.service.ServiceAvailabilityService;
import com.example.projectmrsisa.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ActionService actionService;

    @Transactional
    @DeleteMapping(value="delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin', 'fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity deleteService(@PathVariable Integer id){
        try {
            serviceService.deleteServiceById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-service-availability/{id}")
    @PreAuthorize("hasAnyRole('retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<List<ServiceAvailabilityDTO>> getServiceAvailability(@PathVariable Integer id) {
        try {
            Optional<Service> service = serviceService.findServiceById(id);
            if (!service.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<ServiceAvailability> availabilities = serviceAvailabilityService.getServiceAvailabilitiesForService(id);
            List<ServiceAvailabilityDTO> serviceAvailabilityDTOS = new ArrayList<>();
            for (ServiceAvailability sa: availabilities) {
                serviceAvailabilityDTOS.add(new ServiceAvailabilityDTO(sa));
            }
            return new ResponseEntity<>(serviceAvailabilityDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
