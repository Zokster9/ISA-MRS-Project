package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.RevisionDTO;
import com.example.projectmrsisa.dto.ServiceAverageRatingDTO;
import com.example.projectmrsisa.model.Rating;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.Revision;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/revisions")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/findAll")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<List<RevisionDTO>> findUnansweredRevisions(){
        List<Revision> revisions = new ArrayList<>();
        try{
            revisions = revisionService.findUnansweredRevisions();
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<RevisionDTO> revisionDTOS = new ArrayList<>();
        for (Revision revision : revisions){
            revisionDTOS.add(new RevisionDTO(revision));
        }
        return new ResponseEntity<>(revisionDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/updateRevision")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<RevisionDTO> updateRevision(@RequestBody RevisionDTO revisionDTO){
        Revision revision;
        //LOCK
        try{
            revision = revisionService.findRevisionById(revisionDTO.getId());
        } catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        try{
            revisionService.updateRevisionAnsweredStatus(revisionDTO.getId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (revisionDTO.isApproved()){
            try{
                revisionService.updateRevisionApprovedStatus(revisionDTO.getId());
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            emailService.sendApprovedRevisionEmail(revision.getRevision(), revision.getRating().getServiceRating(),
                    revision.getRating().getUserRating(), revision.getReservation().getService().getOwner(), revision.getReservation().getClient());
        }
        RevisionDTO revisionDTO1 = new RevisionDTO(revision);
        return new ResponseEntity<>(revisionDTO1, HttpStatus.OK);
    }

    @PostMapping(value="/makeARevision")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<RevisionDTO> makeARevision(@RequestBody RevisionDTO revisionDTO) {
        try {
            Reservation reservation = reservationService.findReservationById(revisionDTO.getReservationId());
            Rating rating = ratingService.save(new Rating(revisionDTO.getOwnerRating(), revisionDTO.getServiceRating()));
            Revision revision = revisionService.save(new Revision(revisionDTO, rating, reservation));
            return new ResponseEntity<>(new RevisionDTO(revision), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user-service-ratings")
    @PreAuthorize("hasAnyRole('retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<List<ServiceAverageRatingDTO>> getAverageRatingForUserServices(Principal principal) {
        try {
            User user = userService.findUserByEmail(principal.getName());
            if (user == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(revisionService.getAverageRatingForUserServices(user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}