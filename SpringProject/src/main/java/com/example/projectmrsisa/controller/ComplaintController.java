package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ComplaintDTO;
import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.service.ComplaintService;
import com.example.projectmrsisa.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Complaint complaint = complaintService.findComplaintById(complaintDTO.getId());
        try{
            complaintService.updateComplaintStatus(complaintDTO.getId());
            complaintService.updateComplaintResponse(complaintDTO.getResponse(), complaintDTO.getId());
            emailService.sendComplaintEmailClient(complaint.getComplaint(), complaint.getReservation().getClient());
            emailService.sendComplaintEmailPrivilegedUser(complaint.getComplaint(), complaint.getReservation().getService().getOwner());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(complaintDTO, HttpStatus.OK);
    }
}
