package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
import com.example.projectmrsisa.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/retreats")
public class RetreatController {

    @Autowired
    private RetreatService retreatService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RevisionService revisionService;
    
    private Validator validator = new Validator();

    @GetMapping(value="/getAll", produces = "application/json")
    public ResponseEntity<List<RetreatDTO>> getRetreats() {
        try {
            List<Retreat> retreats = retreatService.getRetreats();
            List<RetreatDTO> retreatDTOS = new ArrayList<>();
            for (Retreat retreat : retreats) {
                if (retreat.isDeleted()) continue;
                retreatDTOS.add(new RetreatDTO(retreat, "retreat", revisionService.getAverageRatingForService(retreat.getId())));
            }
            return new ResponseEntity<>(retreatDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create-retreat", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<RetreatDTO> createRetreat(@RequestBody RetreatDTO retreatDTO, Principal loggedUser) {
        User retreatOwner;
        try {
            retreatOwner = userService.findUserByEmail(loggedUser.getName());
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (!validator.validAddress(retreatDTO.getCountry(), retreatDTO.getCity(), retreatDTO.getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validateRetreatData(retreatDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Set<Tag> additionalServices;
        try {
            a = addressService.getAddress(new Address(retreatDTO.getCountry(), retreatDTO.getCity(), retreatDTO.getStreet()));
            additionalServices = tagService.findTags(retreatDTO.getAdditionalServices(), "retreat");
            Retreat retreat = retreatService.addRetreat(new Retreat(retreatDTO, a, additionalServices, retreatOwner));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<RetreatDTO> getRetreatById(@PathVariable Integer id) {
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            return new ResponseEntity<>(new RetreatDTO(retreat, revisionService.getAverageRatingForService(retreat.getId())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-retreat/{id}", produces = "application/json")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<RetreatDTO> updateRetreat(@PathVariable Integer id, @RequestBody RetreatDTO retreatDTO) {
        if (!validator.validAddress(retreatDTO.getCountry(), retreatDTO.getCity(), retreatDTO.getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validateRetreatData(retreatDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            Set<Tag> newAdditionalServices = tagService.findTags(retreatDTO.getAdditionalServices(), "retreat");
            retreat = retreatService.updateRetreat(retreat, retreatDTO, newAdditionalServices);
            return new ResponseEntity<>(new RetreatDTO(retreat), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-retreat/{id}")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<RetreatDTO> deleteRetreat(@PathVariable Integer id) {
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (retreat == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            retreatService.deleteRetreat(id);
            return new ResponseEntity<>(new RetreatDTO(retreat), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add-availability/{id}")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<ServiceAvailabilityDTO> addRetreatAvailability(@PathVariable Integer id, @RequestBody ServiceAvailabilityDTO serviceAvailabilityDTO) {
        if (!validator.validServiceAvailability(serviceAvailabilityDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            if (retreat == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            ServiceAvailability serviceAvailability = new ServiceAvailability(serviceAvailabilityDTO, retreat);
            serviceAvailability = serviceAvailabilityService.addAvailability(id, serviceAvailability);
            if (serviceAvailability == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
            return new ResponseEntity<>(new ServiceAvailabilityDTO(serviceAvailability), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value = "/add-action/{id}")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<ActionDTO> addAction(@PathVariable Integer id, @RequestBody ActionDTO actionDTO, Principal loggedUser) {
        if (!validator.validAction(actionDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            if (retreat == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (!retreat.getOwner().getEmail().equals(loggedUser.getName())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            if (!reservationService.checkIfReservationsExistForDate(id, actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (!actionService.actionAlreadyExists(retreat.getActions(), actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> additionalServices = tagService.findTags(actionDTO.getAdditionalServices(), "retreat");
            Action action = new Action(actionDTO, additionalServices, retreat);
            action = actionService.addAction(action);
            retreat = retreatService.addAction(retreat, action);
            List<String> emails = subscriptionService.findClientsWithSubscription(clientService.findAll(), id);
            emailService.sendSubscriptionEmails(emails);
            return new ResponseEntity<>(new ActionDTO(action), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-actions/{id}")
    @PreAuthorize("hasRole('retreatOwner')")
    public ResponseEntity<List<ActionDTO>> getActions(@PathVariable Integer id) {
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            if (retreat == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<ActionDTO> actionDTOS = new ArrayList<>();
            for (Action action: retreat.getActions()) {
                actionDTOS.add(new ActionDTO(action));
            }
            return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
