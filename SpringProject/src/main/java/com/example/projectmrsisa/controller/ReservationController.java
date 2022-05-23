package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.*;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private EmailService emailService;

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
    public ResponseEntity<List<RetreatDTO>> getAvailableRetreats(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Retreat> retreats = retreatService.getRetreats();
            List<RetreatDTO> retreatDTOs = new ArrayList<>();
            for (Retreat retreat : retreats) {
                if (serviceAvailabilityService.isAvailable(retreat.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime())) {
                    if (!reservationService.isReserved(retreat.getId(), reservationQueryDTO.getFromDate(),
                            reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime(), user.getId())) {
                        retreatDTOs.add(new RetreatDTO(retreat));
                    }
                }
            }
            return new ResponseEntity<>(retreatDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/ship/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ShipDTO>> getAvailableShips(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Ship> ships = shipService.getShips();
            List<ShipDTO> shipDTOs = new ArrayList<>();
            for (Ship ship : ships) {
                if (serviceAvailabilityService.isAvailable(ship.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime())) {
                    if (!reservationService.isReserved(ship.getId(), reservationQueryDTO.getFromDate(),
                            reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime(), user.getId())) {
                        shipDTOs.add(new ShipDTO(ship));
                    }
                }
            }
            return new ResponseEntity<>(shipDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/adventure/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<AdventureDTO>> getAvailableAdventures(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Adventure> adventures = adventureService.getAdventures();
            List<AdventureDTO> adventureDTOs = new ArrayList<>();
            for (Adventure adventure : adventures) {
                if (serviceAvailabilityService.isAvailable(adventure.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime())) {
                    if (!reservationService.isReserved(adventure.getId(), reservationQueryDTO.getFromDate(),
                            reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime(), user.getId())) {
                        adventureDTOs.add(new AdventureDTO(adventure));
                    }
                }
            }
            return new ResponseEntity<>(adventureDTOs, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/makeAReservation")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ReservationDTO> makeAReservation(Principal principal, @RequestBody ReservationDTO reservationDTO) {
        Client client;
        Service service;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            service = serviceService.findById(reservationDTO.getServiceId());
            Reservation reservation = reservationService.addReservation(new Reservation(reservationDTO, service, client));
            ReservationDTO resDTO = new ReservationDTO(reservation);
            emailService.sendReservationConfirmation(resDTO);
            return new ResponseEntity<>(resDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/getNonComplainedReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getNonComplainedReservations(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsFinishedReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (complaintService.findComplaintByReservationId(reservation.getId()) == null) {
                    reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
