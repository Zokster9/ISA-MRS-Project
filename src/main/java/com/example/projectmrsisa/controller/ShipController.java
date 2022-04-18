package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.model.ShipOwner;
import com.example.projectmrsisa.service.AddressService;
import com.example.projectmrsisa.service.ShipService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO) {
        //TODO: Dobaviti vlasnika broda uz pomoc JWT
        ShipOwner shipOwner;
        try {
            shipOwner = userService.getShipOwnerById(2);
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
}
