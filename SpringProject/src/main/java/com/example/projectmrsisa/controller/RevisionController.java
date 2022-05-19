package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.RevisionDTO;
import com.example.projectmrsisa.model.Revision;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.RevisionService;
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
@RequestMapping(value="/revisions")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @Autowired
    private EmailService emailService;

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

}
