package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.AddressService;
import com.example.projectmrsisa.service.ServiceAvailabilityService;
import com.example.projectmrsisa.service.ShipService;
import com.example.projectmrsisa.service.UserService;
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

    private String[] fishingEquipment = {"Hooks", "Lines", "Floats", "Rods", "Reels", "Baits", "Lures", "Spears", "Nets", "Gaffs", "Traps"};
    private String[] navigationEquipment = {"Gyro Compass", "Radar", "Autopilot", "Echo sounder", "Magnetic Compass", "ARPA", "GPS Receiver", "Navigation lamps"};

    @Autowired
    private ShipService shipService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @GetMapping(value="/getAll", produces = "application/json")
    public ResponseEntity<List<ShipDTO>> getShips() {
        try {
            List<Ship> ships = shipService.getShips();
            List<ShipDTO> shipDTOS = new ArrayList<>();
            for (Ship ship : ships) {
                shipDTOS.add(new ShipDTO(ship));
            }
            return new ResponseEntity<>(shipDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/create-ship", consumes = "application/json")
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO) {
        //TODO: Dobaviti vlasnika broda uz pomoc JWT
        User shipOwner;
        try {
            shipOwner = userService.findUserById(2);
        }catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (!validAddress(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validateShipData(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        try {
            a = addressService.getAddress(new Address(shipDTO.getCountry(), shipDTO.getCity(), shipDTO.getStreet()));
            Ship ship = shipService.addShip(new Ship(shipDTO, a, shipOwner));
            return new ResponseEntity<ShipDTO>(new ShipDTO(ship), HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    private boolean validateShipData(ShipDTO shipDTO) {
        if (shipDTO.getName() == null || shipDTO.getName().equals("")){
            return false;
        }
        if (shipDTO.getDescription() == null || shipDTO.getDescription().equals("")){
            return false;
        }
        if (shipDTO.getType() == null || shipDTO.getType().equals("")) {
            return false;
        }
        if (shipDTO.getLength() <= 5) {
            return false;
        }
        if (shipDTO.getEngineNum() == null || shipDTO.getEngineNum().equals("")) {
            return false;
        }
        if (shipDTO.getEnginePower() <= 40) {
            return false;
        }
        if (shipDTO.getMaxSpeed() == null || shipDTO.getMaxSpeed().matches("[1-9][0-9]* 'km/h'")) {
            return false;
        }
        if (shipDTO.getCapacity() <= 0) {
            return false;
        }
        for (String pic: shipDTO.getPictures()) {
            if (pic.equals("")) return false;
            if (!pic.endsWith(".png") && !pic.endsWith(".jpg") && !pic.endsWith(".jpeg")) return false;
        }
        for (String rc: shipDTO.getRulesOfConduct()) {
            if (rc.equals("")) return false;
            if (!rc.equals("Smoking disallowed") && !rc.equals("Pet friendly") && !rc.equals("Parties disallowed") && !rc.equals("Drinking disallowed") && !rc.equals("Weapons disallowed")) return false;
        }
        ArrayList<String> fishEq = new ArrayList<>(Arrays.asList(fishingEquipment));
        for (String fe: shipDTO.getFishingEquipment()) {
            if (fe.equals("")) return false;
            if (!fishEq.contains(fe)) return false;
        }
        ArrayList<String> navEq = new ArrayList<>(Arrays.asList(navigationEquipment));
        for (String ne: shipDTO.getNavigationEquipment()) {
            if (ne.equals("")) return false;
            if (!navEq.contains(ne)) return false;
        }
        return shipDTO.getReservationCancellationConditions() != null;
    }

    private boolean validAddress(ShipDTO shipDTO) {
        if (shipDTO.getCountry() == null || shipDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        if (shipDTO.getCity() == null || shipDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        return shipDTO.getStreet() != null && !shipDTO.getStreet().equals("");
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> getShipById(@PathVariable Integer id) {
        try {
            Ship ship = shipService.findShipById(id);
            if (ship == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(value = "/update-ship/{id}", produces = "application/json")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> updateRetreat(@PathVariable Integer id, @RequestBody ShipDTO shipDTO) {
        if (!validAddress(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validateShipData(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // TODO: provera da li postoje rezervacije za brod
        try {
            Ship ship = shipService.findShipById(id);
            ship = shipService.updateRetreat(ship, shipDTO);
            return new ResponseEntity<>(new ShipDTO(ship), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping(value = "/delete-ship/{id}")
    @PreAuthorize("hasRole('shipOwner')")
    public ResponseEntity<ShipDTO> deleteShip(@PathVariable Integer id) {
        try {
            // TODO: provera da li postoje rezervacije za brod
            Ship ship = shipService.findShipById(id);
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
        if (!validServiceAvailability(serviceAvailabilityDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    private boolean validServiceAvailability(ServiceAvailabilityDTO serviceAvailabilityDTO) {
        Date today = new Date();
        if (serviceAvailabilityDTO.getDateFrom() == null) return false;
        if (serviceAvailabilityDTO.getDateTo() == null) return false;
        Date dateFrom = serviceAvailabilityDTO.getDateFrom();
        Date dateTo = serviceAvailabilityDTO.getDateTo();
        if (dateFrom.compareTo(today) < 0) return false;
        if (dateTo.compareTo(today) < 0 ) return false;
        if (dateFrom.compareTo(dateTo) > 0) return false;
        if (dateFrom.compareTo(dateTo) == 0) {
            return Integer.parseInt(serviceAvailabilityDTO.getTimeFrom().split(":")[0]) * 60 + Integer.parseInt(serviceAvailabilityDTO.getTimeFrom().split(":")[1])
                    < Integer.parseInt(serviceAvailabilityDTO.getTimeTo().split(":")[0]) * 60 + Integer.parseInt(serviceAvailabilityDTO.getTimeTo().split(":")[1]);
        }
        return true;
    }
}
