package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ComplaintDTO;
import com.example.projectmrsisa.model.Complaint;
import com.example.projectmrsisa.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

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
}
