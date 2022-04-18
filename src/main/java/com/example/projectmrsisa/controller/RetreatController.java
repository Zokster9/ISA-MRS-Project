package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.AddressDTO;
import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.model.RetreatOwner;
import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.service.AddressService;
import com.example.projectmrsisa.service.RetreatService;
import com.example.projectmrsisa.service.TagService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value="/retreats")
public class RetreatController {

    @Autowired
    private RetreatService retreatService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<RetreatDTO> createRetreat(@RequestBody RetreatDTO retreatDTO) {
        //TODO: Dobaviti vlasnika broda uz pomoc JWT
        //  privremeno
        RetreatOwner retreatOwner;
        try {
            retreatOwner = userService.getRetreatOwnerById(1);
        } catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        if (!validAddress(retreatDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validateRetreatData(retreatDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Set<Tag> additionalServices;
        try {
            a = addressService.getAddress(new Address(retreatDTO.getCountry(), retreatDTO.getCity(), retreatDTO.getStreet()));
            additionalServices = tagService.findTags(retreatDTO.getAdditionalServices());
            Retreat retreat = retreatService.addRetreat(new Retreat(retreatDTO, a, additionalServices, retreatOwner));
            return new ResponseEntity<>(new RetreatDTO(retreat), HttpStatus.OK);
        }catch (Exception e) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    private boolean validateRetreatData(RetreatDTO retreatDTO) {
        if (retreatDTO.getName() == null || retreatDTO.getName().equals("")){
            return false;
        }
        if (retreatDTO.getDescription() == null || retreatDTO.getDescription().equals("")){
            return false;
        }
        if (retreatDTO.getNumOfRooms() <= 0) {
            return false;
        }
        for (String as: retreatDTO.getAdditionalServices()) {
            if (as.equals("") || as.length() > 14) return false;
        }
        for (String rc: retreatDTO.getRulesOfConduct()) {
            if (rc.equals("")) return false;
            if (!rc.equals("Inside smoking disallowed") && !rc.equals("Pet friendly") && !rc.equals("Parties disallowed") && !rc.equals("Furniture braking is charged extra")) return false;
        }
        for (String pic: retreatDTO.getPictures()) {
            if (pic.equals("")) return false;
            if (!pic.endsWith(".png") && !pic.endsWith(".jpg") && !pic.endsWith(".jpeg")) return false;
        }
        return retreatDTO.getNumOfBeds() > 0;
    }

    private boolean validAddress(RetreatDTO retreatDTO) {
        if (retreatDTO.getCountry().equals("") || retreatDTO.getCountry() == null || !retreatDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (retreatDTO.getCity().equals("") || retreatDTO.getCity() == null || !retreatDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        return !retreatDTO.getStreet().equals("") && retreatDTO.getStreet() != null;
    }
}
