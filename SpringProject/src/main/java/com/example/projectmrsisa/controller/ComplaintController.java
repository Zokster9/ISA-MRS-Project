package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ComplaintDTO;
import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.service.ComplaintService;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping(value="/findAll")
    @PreAuthorize("hasAnyRole('mainAdmin','admin')")
    public ResponseEntity<List<ComplaintDTO>> findUnansweredComplaints(){
        List<Complaint> complaints = new ArrayList<>();
        try{
            complaints = complaintService.findUnansweredComplaints();
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ComplaintDTO> complaintDTOS = new ArrayList<>();
        for (Complaint c : complaints){
            complaintDTOS.add(new ComplaintDTO(c));
        }
        return new ResponseEntity<>(complaintDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/response")
    @PreAuthorize("hasAnyRole('mainAdmin','admin')")
    @Transactional
    public ResponseEntity<ComplaintDTO> answerComplaint(@RequestBody ComplaintDTO complaintDTO){
        Complaint complaint;
        //LOCK
        try {
            complaint = complaintService.findComplaintById(complaintDTO.getId());
        } catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        try{
            complaintService.updateComplaintStatus(complaintDTO.getId());
            complaintService.updateComplaintResponse(complaintDTO.getResponse(), complaintDTO.getId());
            emailService.sendComplaintEmailClient(complaint.getComplaint(), complaint.getReservation().getClient());
            emailService.sendComplaintEmailPrivilegedUser(complaint.getComplaint(), complaint.getReservation().getService().getOwner());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ComplaintDTO complaintDTO1 = new ComplaintDTO(complaint);
        return new ResponseEntity<>(complaintDTO1, HttpStatus.OK);
    }

    @PostMapping(value="/makeAComplaint")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ComplaintDTO> makeAComplaint(@RequestBody ComplaintDTO complaintDTO) {
        try {
            Reservation reservation = reservationService.findReservationById(complaintDTO.getReservationId());
            Complaint complaint = complaintService.save(new Complaint(complaintDTO, reservation));
            return new ResponseEntity<>(new ComplaintDTO(complaint), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
