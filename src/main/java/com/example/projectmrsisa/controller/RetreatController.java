package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.service.RetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/retreats")
public class RetreatController {

    @Autowired
    private RetreatService retreatService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RetreatDTO> getRetreatById(@PathVariable Integer id) {
        // TODO: provera JWT
        try {
            Retreat retreat = retreatService.getRetreatById(id);
            return new ResponseEntity<>(new RetreatDTO(retreat), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
