package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.service.*;
import com.example.projectmrsisa.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(value="/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RevisionService revisionService;
    
    private Validator validator = new Validator();

    @GetMapping(value="/getAll", produces = "application/json")
    public ResponseEntity<List<ShipDTO>> getShips() {
        try {
            List<Ship> ships = shipService.getShips();
            List<ShipDTO> shipDTOS = new ArrayList<>();
            for (Ship ship : ships) {
                if(ship.isDeleted()) continue;
                shipDTOS.add(new ShipDTO(ship, "ship", revisionService.getAverageRatingForService(ship.getId())));
            }
            return new ResponseEntity<>(shipDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/create-ship", consumes = "application/json")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO, Principal loggedUser) {
        User shipOwner;
        try {
            shipOwner = userService.findUserByEmail(loggedUser.getName());
        }catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (!validator.validAddress(shipDTO.getCountry(), shipDTO.getCity(), shipDTO.getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validateShipData(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Set<Tag> additionalServices;
        try {
            a = addressService.getAddress(new Address(shipDTO.getCountry(), shipDTO.getCity(), shipDTO.getStreet()));
            additionalServices = tagService.findTags(shipDTO.getAdditionalServices(), "ship");
            Ship ship = shipService.addShip(new Ship(shipDTO, a, shipOwner, additionalServices));
            return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<ShipDTO> getShipById(@PathVariable Integer id) {
        try {
            Ship ship = shipService.findShipById(id);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(new ShipDTO(ship, revisionService.getAverageRatingForService(ship.getId())), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-ship/{id}", produces = "application/json")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> updateRetreat(@PathVariable Integer id, @RequestBody ShipDTO shipDTO) {
        if (!validator.validAddress(shipDTO.getCountry(), shipDTO.getCity(), shipDTO.getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validateShipData(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        try {
            Ship ship = shipService.findShipById(id);
            Set<Tag> newAdditionalServices = tagService.findTags(shipDTO.getAdditionalServices(), "ship");
            ship = shipService.updateShip(ship, shipDTO, newAdditionalServices);
            return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-ship/{id}")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> deleteShip(@PathVariable Integer id) {
        try {
            Ship ship = shipService.findShipById(id);
            if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            shipService.deleteShipById(id);
            return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add-availability/{id}")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ServiceAvailabilityDTO> addRetreatAvailability(@PathVariable Integer id, @RequestBody ServiceAvailabilityDTO serviceAvailabilityDTO) {
        if (!validator.validServiceAvailability(serviceAvailabilityDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Ship ship = shipService.findShipById(id);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            ServiceAvailability serviceAvailability = new ServiceAvailability(serviceAvailabilityDTO, ship);
            serviceAvailability = serviceAvailabilityService.addAvailability(id, serviceAvailability);
            if (serviceAvailability == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
            return new ResponseEntity<>(new ServiceAvailabilityDTO(serviceAvailability), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value = "/add-action/{id}")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ActionDTO> addAction(@PathVariable Integer id, @RequestBody ActionDTO actionDTO, Principal loggedUser) {
        if (!validator.validAction(actionDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Ship ship = shipService.findShipById(id);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (!ship.getOwner().getEmail().equals(loggedUser.getName())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            if (!reservationService.checkIfReservationsExistForDate(id, actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            if (!actionService.actionAlreadyExists(ship.getActions(), actionDTO.getDateFrom(), actionDTO.getDateTo())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> additionalServices = tagService.findTags(actionDTO.getAdditionalServices(), "ship");
            Action action = new Action(actionDTO, additionalServices, ship);
            action = actionService.addAction(action);
            ship = shipService.addAction(ship, action);
            List<String> emails = subscriptionService.findClientsWithSubscription(clientService.findAll(), id);
            emailService.sendSubscriptionEmails(emails);
            return new ResponseEntity<>(new ActionDTO(action), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-actions/{id}")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<List<ActionDTO>> getActions(@PathVariable Integer id) {
        try {
            Ship ship = shipService.findShipById(id);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<ActionDTO> actionDTOS = new ArrayList<>();
            for (Action action: ship.getActions()) {
                actionDTOS.add(new ActionDTO(action));
            }
            return new ResponseEntity<>(actionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
