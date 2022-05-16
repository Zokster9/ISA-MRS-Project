package com.example.projectmrsisa.controller;


import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.dto.AdventureDTO;
import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.dto.ServiceDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
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

    @PostMapping(value="/create-adventure",consumes = "application/json")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<AdventureDTO> createAdventure(@RequestBody AdventureDTO adventureDTO, Principal principal) {
        User fishingInstructor = userService.findUserByEmail(principal.getName());

        if (adventureDTO.getName().length() < 5 || adventureDTO.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getDescription().length() < 5 || adventureDTO.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getPrice() < 5) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getMaxNumOfPeople() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getReservationCancellationConditions().length() < 5 || adventureDTO.getReservationCancellationConditions() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getInstructorBiography().length() < 5 || adventureDTO.getInstructorBiography() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getStreet() == null || adventureDTO.getCountry() == null || adventureDTO.getCity() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Address address = addressService.getAddress(new Address(adventureDTO.getCountry(), adventureDTO.getCity(), adventureDTO.getStreet()));
        Set<Tag> additionalServices = tagService.findTags(adventureDTO.getAdditionalServices(), "adventure");
        Adventure adventure = adventureService.addAdventure(new Adventure(adventureDTO, address, fishingInstructor, additionalServices));
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAdventure/{id}", produces = "application/json")
    @PreAuthorize("hasAnyRole('fishingInstructor', 'admin', 'mainAdmin')")
    public ResponseEntity<AdventureDTO> getAdventureById(@PathVariable Integer id) {
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping(value = "/delete-adventure/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity deleteAdventure(@PathVariable Integer id){
        try {
            //TODO: Provera da li je servis rezervisan!
            adventureService.deleteAdventureById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validAddress(AdventureDTO adventureDTO) {
        if (adventureDTO.getCountry().equals("") || adventureDTO.getCountry() == null || !adventureDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (adventureDTO.getCity().equals("") || adventureDTO.getCity() == null || !adventureDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        return !adventureDTO.getStreet().equals("") && adventureDTO.getStreet() != null;
    }

    @Transactional
    @PutMapping(value="/updateAdventure/{id}", produces = "application/json")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<AdventureDTO> updateRetreat(@PathVariable Integer id, @RequestBody AdventureDTO adventureDTO){
        if (!validAddress(adventureDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (adventureDTO.getName().length() < 5 || adventureDTO.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getDescription().length() < 5 || adventureDTO.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getPrice() < 5) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getMaxNumOfPeople() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getReservationCancellationConditions().length() < 5 || adventureDTO.getReservationCancellationConditions() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getInstructorBiography().length() < 5 || adventureDTO.getInstructorBiography() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //TODO: provera da li postoje rezervacije za avanturu
        try{
            Adventure adventure = adventureService.findAdventureById(id);
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
                adventureDTOS.add(new AdventureDTO(adventure));
            }
            return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add-availability/{id}")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<ServiceAvailabilityDTO> addAdventureAvailability(@PathVariable Integer id, @RequestBody ServiceAvailabilityDTO serviceAvailabilityDTO) {
        if (!validServiceAvailability(serviceAvailabilityDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        if (!validAction(actionDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            if (adventure == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (!adventure.getOwner().getEmail().equals(loggedUser.getName())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            if (!reservationService.checkIfReservationsExistForDate(id, actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (!actionService.actionAlreadyExists(adventure.getActions(), actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> additionalServices = tagService.findTags(actionDTO.getAdditionalServices(), "adventure");
            Action action = new Action(actionDTO, additionalServices);
            action = actionService.addAction(action);
            adventure = adventureService.addAction(adventure, action);
            List<String> emails = subscriptionService.findClientsWithSubscription(clientService.findAll(), id);
            emailService.sendSubscriptionEmails(emails);
            return new ResponseEntity<>(new ActionDTO(action), HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validServiceAvailability(ServiceAvailabilityDTO serviceAvailabilityDTO) {
        if (!validDates(serviceAvailabilityDTO.getDateFrom(), serviceAvailabilityDTO.getDateTo(), serviceAvailabilityDTO.getTimeFrom(), serviceAvailabilityDTO.getTimeTo())) return false;
        return true;
    }
    
    private boolean validAction(ActionDTO actionDTO) {
        if (!validDates(actionDTO.getDateFrom(), actionDTO.getDateTo(), actionDTO.getTimeFrom(), actionDTO.getTimeTo())) return false;
        System.out.println(actionDTO.getMaxNumOfPeople());
        if (actionDTO.getMaxNumOfPeople() <= 0) return false;
        for (String as: actionDTO.getAdditionalServices()) {
            if (as.equals("")) return false;
        }
        return !(actionDTO.getPrice() <= 0);
    }

    private boolean validDates(Date dateFrom, Date dateTo, String timeFrom, String timeTo) {
        Date today = new Date();
        if (dateFrom == null || dateTo == null || timeFrom == null || timeTo == null) return false;
        if (dateFrom.compareTo(today) < 0) return false;
        if (dateTo.compareTo(today) < 0 ) return false;
        if (dateFrom.compareTo(dateTo) > 0) return false;
        if (dateFrom.compareTo(dateTo) == 0) {
            return Integer.parseInt(timeFrom) * 60 + Integer.parseInt(timeFrom)
                    < Integer.parseInt(timeTo) * 60 + Integer.parseInt(timeTo);
        }
        return true;
    }

    @GetMapping(value = "/get-actions/{id}")
    @PreAuthorize("hasRole('fishing-instructor')")
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
}
