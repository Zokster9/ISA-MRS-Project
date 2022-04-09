package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.service.RetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/retreats")
public class RetreatController {

    @Autowired
    private RetreatService retreatService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RetreatDTO> createRetreat(@RequestBody RetreatDTO retreatDTO) {
        //TODO: Dobaviti vlasnika broda uz pomoc JWT
        if (!validateRetreatData(retreatDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Validirani podaci, dalje ide obrada...");
        //TODO: Tip je Retreat, ne RetreatDTO
        //TODO: Pozvati funkciju RetreatService klase koja ce da pozove funkciju na repozitorijumu za dodavanje vikendice
        //TODO: Treba vratiti na front napravljenu vikendicu
        return new ResponseEntity<>(retreatDTO, HttpStatus.OK);
    }

    private boolean validateRetreatData(RetreatDTO retreatDTO) {
        if (retreatDTO.getName() == null || retreatDTO.getName().equals("")){
            return false;
        }
        if (retreatDTO.getDescription() == null || retreatDTO.getDescription().equals("")){
            return false;
        }
        if (retreatDTO.getCountry() == null || retreatDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        if (retreatDTO.getCity() == null || retreatDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$/")) {
            return false;
        }
        if (retreatDTO.getAddress() == null ||retreatDTO.getAddress().equals("")) {
            return false;
        }
        if (retreatDTO.getNumOfRooms() <= 0) {
            return false;
        }
        return retreatDTO.getNumOfBeds() > 0;
    }
}
