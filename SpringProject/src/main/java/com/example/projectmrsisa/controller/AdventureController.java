package com.example.projectmrsisa.controller;


import com.example.projectmrsisa.dto.AdventureDTO;
import com.example.projectmrsisa.dto.ServiceDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.AddressService;
import com.example.projectmrsisa.service.AdventureService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping(consumes = "application/json", produces = "application/json")
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
        Adventure adventure = adventureService.addAdventure(new Adventure(adventureDTO, address, fishingInstructor));
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAdventure/{id}", produces = "application/json")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity<AdventureDTO> getAdventureById(@PathVariable Integer id) {
        try {
            Adventure adventure = adventureService.findAdventureById(id);
            return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping(value = "/deleteService")
    @PreAuthorize("hasRole('fishingInstructor')")
    public ResponseEntity deleteService(@RequestBody ServiceDTO serviceDTO){
        try {
            //TODO: Provera da li je servis rezervisan!
            adventureService.deleteAdventureById(serviceDTO.getId());
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
        try{
            Adventure adventure = adventureService.findAdventureById(id);
            //todo:Tagovi
            adventure = adventureService.updateAdventure(adventure, adventureDTO /*,tags*/);
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
                adventureDTOS.add(new AdventureDTO(adventure));
            }
            return new ResponseEntity<>(adventureDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
