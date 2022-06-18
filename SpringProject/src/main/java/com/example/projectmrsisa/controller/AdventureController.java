package com.example.projectmrsisa.controller;


import com.example.projectmrsisa.dto.*;
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
@RequestMapping(value = "/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RevisionService revisionService;
    
    private Validator validator = new Validator();

    @PostMapping(value="/create-adventure",consumes = "application/json")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<AdventureDTO> createAdventure(@RequestBody AdventureDTO adventureDTO, Principal principal) {
        User fishingInstructor = userService.findUserByEmail(principal.getName());
        if (!validator.validateAdventure(adventureDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address address = addressService.getAddress(new Address(adventureDTO.getCountry(), adventureDTO.getCity(), adventureDTO.getStreet()));
        Set<Tag> additionalServices = tagService.findTags(adventureDTO.getAdditionalServices(), "adventure");
        Adventure adventure = adventureService.addAdventure(new Adventure(adventureDTO, address, fishingInstructor, additionalServices));
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<AdventureDTO> getAdventureById(@PathVariable Integer id) {
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            return new ResponseEntity<>(new AdventureDTO(adventure, revisionService.getAverageRatingForService(adventure.getId())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-adventure/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity deleteAdventure(@PathVariable Integer id){
        try {
            if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
            adventureService.deleteAdventureById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/updateAdventure/{id}", produces = "application/json")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<AdventureDTO> updateRetreat(@PathVariable Integer id, @RequestBody AdventureDTO adventureDTO){
        if (!validator.validAddress(adventureDTO.getCountry(), adventureDTO.getCity(), adventureDTO.getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validateAdventure(adventureDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try{
            Adventure adventure = adventureService.findAdventureById(id);
            if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> newAdditionalServices = tagService.findTags(adventureDTO.getAdditionalServices(), "adventure");
            adventure = adventureService.updateAdventure(adventure, adventureDTO, newAdditionalServices);
            return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/getAll", produces = "application/json")
    public ResponseEntity<List<AdventureDTO>> getAdventures() {
        try {
            List<Adventure> adventures = adventureService.getAdventures();
            List<AdventureDTO> adventureDTOS = new ArrayList<>();
            for (Adventure adventure : adventures) {
                if (adventure.isDeleted()) continue;
                adventureDTOS.add(new AdventureDTO(adventure, "adventure", revisionService.getAverageRatingForService(adventure.getId())));
            }
            return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add-availability/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<ServiceAvailabilityDTO> addAdventureAvailability(@PathVariable Integer id, @RequestBody ServiceAvailabilityDTO serviceAvailabilityDTO) {
        if (!validator.validServiceAvailability(serviceAvailabilityDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            if (adventure == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            ServiceAvailability serviceAvailability = new ServiceAvailability(serviceAvailabilityDTO, adventure);
            serviceAvailability = serviceAvailabilityService.addAvailability(id, serviceAvailability);
            if (serviceAvailability == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
            return new ResponseEntity<>(new ServiceAvailabilityDTO(serviceAvailability), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value="/add-action/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<ActionDTO> addAction(@PathVariable Integer id, @RequestBody ActionDTO actionDTO, Principal loggedUser){
        if (!validator.validAction(actionDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            if (adventure == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (!adventure.getOwner().getEmail().equals(loggedUser.getName())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            if (!reservationService.checkIfReservationsExistForDate(id, actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (!actionService.actionAlreadyExists(adventure.getActions(), actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> additionalServices = tagService.findTags(actionDTO.getAdditionalServices(), "adventure");
            Action action = new Action(actionDTO, additionalServices, adventure);
            action = actionService.addAction(action);
            adventure = adventureService.addAction(adventure, action);
            List<String> emails = subscriptionService.findClientsWithSubscription(clientService.findAll(), id);
            emailService.sendSubscriptionEmails(emails);
            return new ResponseEntity<>(new ActionDTO(action), HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-actions/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<List<ActionDTO>> getActions(@PathVariable Integer id) {
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            if (adventure == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<ActionDTO> actionDTOS = new ArrayList<>();
            for (Action action: adventure.getActions()) {
                actionDTOS.add(new ActionDTO(action));
            }
            return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/getInstructorsAdventures/{id}")
    public ResponseEntity<List<AdventureDTO>> getInstructorsAdventures(@PathVariable Integer id) {
        try {
            List<Adventure> adventures = adventureService.findOwnersAdventures(id);
            List<AdventureDTO> adventureDTOS = new ArrayList<>();
            for (Adventure adventure : adventures) {
                if (!adventure.isDeleted()) {
                    adventureDTOS.add(new AdventureDTO(adventure, "adventure",
                            revisionService.getAverageRatingForService(adventure.getId())));
                }
            }
            return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
