package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO) {
        //TODO: Dobaviti vlasnika broda uz pomoc JWT
        if (!validateShipData(shipDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //TODO: Tip je Ship, ne ShipDTO
        //TODO: Pozvati funkciju ShipService klase koja ce da pozove funkciju na repozitorijumu za dodavanje broda
        //TODO: Treba vratiti na front napravljen brod
        return new ResponseEntity<>(shipDTO, HttpStatus.OK);
    }

    private boolean validateShipData(ShipDTO shipDTO) {
        if (shipDTO.getName() == null || shipDTO.getName().equals("")){
            return false;
        }
        if (shipDTO.getDescription() == null || shipDTO.getDescription().equals("")){
            return false;
        }
        if (shipDTO.getCountry() == null || shipDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        if (shipDTO.getCity() == null || shipDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        if (shipDTO.getAddress() == null ||shipDTO.getAddress().equals("")) {
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
            System.out.println("Losa ti brzina");
            return false;
        }
        if (shipDTO.getCapacity() <= 0) {
            return false;
        }
        return shipDTO.getReservationCancellationConditions() != null;
    }
}
