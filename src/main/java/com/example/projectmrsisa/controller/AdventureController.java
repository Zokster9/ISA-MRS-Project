package com.example.projectmrsisa.controller;


import com.example.projectmrsisa.dto.AdventureDTO;
import com.example.projectmrsisa.model.Adventure;
import com.example.projectmrsisa.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdventureDTO> createAdventure(@RequestBody AdventureDTO adventureDTO){
        //TODO: jwt za vlasnika avanture
        if (adventureDTO.getName().length() < 5 || adventureDTO.getName() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getDescription().length() < 5 || adventureDTO.getDescription() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getPrice() < 5) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getMaxNumOfPeople() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getReservationCancellationConditions().length() < 5 || adventureDTO.getReservationCancellationConditions() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (adventureDTO.getInstructorBiography().length() < 5 || adventureDTO.getInstructorBiography() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //TODO: Tip je Adventure, ne AdventureDTO
        AdventureDTO ad = adventureService.save(adventureDTO);
        //TODO: Umesto ad treba konstruktor AdventureDTO(Adventure adventure)
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

}
