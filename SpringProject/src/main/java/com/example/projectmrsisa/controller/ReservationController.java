package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.*;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/reservations")
public class ReservationController {
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RetreatService retreatService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private AdventureService adventureService;

    @GetMapping(value="/getPrivilegedUserReservations")
    @PreAuthorize("hasAnyRole('fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity<List<ReservationDTO>> getPrivilegedUserReservations(Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
            List<Service> services = serviceService.findOwnersServices(user);
            List<ReservationDTO> reservationsDTO = new ArrayList<>();
            for (Service service: services){
                List<Reservation> reservations = reservationService.findReservationByServiceId(service.getId());
                for (Reservation r : reservations){
                    reservationsDTO.add(new ReservationDTO(r));
                }
            }
            return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/retreat/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<RetreatDTO>> getAvailableRetreats(Principal principal, @RequestBody ReservationQueryDTO reservationQueryDTO){
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Retreat> retreats = retreatService.getRetreats();
            List<RetreatDTO> retreatDTOs = new ArrayList<>();
            return new ResponseEntity<>(retreatDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/ship/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ShipDTO>> getAvailableShips(Principal principal, @RequestBody ReservationQueryDTO reservationQueryDTO){
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Service> services = serviceService.findOwnersServices(user);
            List<ShipDTO> shipDTOs = new ArrayList<>();
            return new ResponseEntity<>(shipDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/adventure/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<AdventureDTO>> getAvailableAdventures(Principal principal, @RequestBody ReservationQueryDTO reservationQueryDTO){
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Service> services = serviceService.findOwnersServices(user);
            List<AdventureDTO> adventureDTOs = new ArrayList<>();
            return new ResponseEntity<>(adventureDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
