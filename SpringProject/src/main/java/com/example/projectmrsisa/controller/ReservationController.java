package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ReservationDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value="/getPrivilegedUserReservations")
    @PreAuthorize("hasAnyRole('fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity<List<ReservationDTO>> getPrivilegedUserReservations(Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
            List<Service> services = serviceService.findOwnersServices(user);
            List<ReservationDTO> reservationsDTO = new ArrayList<ReservationDTO>();
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
}
