package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.dto.AdventureDTO;
import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.ServiceAvailability;
import com.example.projectmrsisa.service.*;
import com.example.projectmrsisa.dto.ServiceDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.security.Principal;

@RestController
@RequestMapping(value="/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RetreatService retreatService;

    @Autowired
    private  ShipService shipService;

    @Transactional
    @DeleteMapping(value="/delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin', 'fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity deleteService(@PathVariable Integer id) {
        try {
            serviceService.deleteServiceById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
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

    @GetMapping(value="/allSubscriptions")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ServiceDTO>> getAllSubscriptions(Principal principal) {
        Client client = (Client) userService.findUserByEmail(principal.getName());
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        for (Service service: client.getSubscriptions()) {
            serviceDTOs.add(new ServiceDTO(service, getServiceType(service)));
        }
        return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
    }

    private String getServiceType(Service service) {
        if (retreatService.getRetreatById(service.getId()) != null)
            return "retreat";
        else if (shipService.findShipById(service.getId()) != null)
            return "ship";
        else
            return "adventure";
    }

    @GetMapping(value="/getAvailableFastReservations/{serviceId}")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ActionDTO>> getAvailableFastReservations(Principal principal, @PathVariable Integer serviceId) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Action> actions = actionService.getActionsByServiceAndCurrentDate(serviceId);
            List<ActionDTO> actionDTOs = new ArrayList<>();
            for (Action action: actions) {
                if (!reservationService.isReserved(serviceId, action.getDateFrom(),
                        action.getDateTo(), action.getTimeFrom(), action.getTimeTo(), client.getId())) {
                    actionDTOs.add(new ActionDTO(action));
                }
            }
            return new ResponseEntity<>(actionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
