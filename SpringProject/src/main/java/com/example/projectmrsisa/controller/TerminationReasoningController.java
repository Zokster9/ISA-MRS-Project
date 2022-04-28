package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.TerminationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.TerminationReasoningService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="termination")
public class TerminationReasoningController {

    @Autowired
    private TerminationReasoningService terminationReasoningService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value="findToTerminate", produces="application/json")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<TerminationReasoningDTO>> findToBeTerminatedUsers(){
        List<TerminationReasoning> unansweredTerminationReasonings = terminationReasoningService.findUnansweredTerminationReasonings();
        List<TerminationReasoningDTO>  trDTO = new ArrayList<TerminationReasoningDTO>();
        for (TerminationReasoning tr : unansweredTerminationReasonings){
            trDTO.add(new TerminationReasoningDTO(tr));
        }
        return new ResponseEntity<>(trDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="declineTermination")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDTO> declineTermination(@RequestParam Integer id, @RequestParam String declineReasoning){
        User user = userService.findUserById(id);
        terminationReasoningService.updateTerminationReasoningByAnsweredStatus(user);
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendTerminationDeclinedEmail(userDTO, declineReasoning);
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="acceptTermination")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDTO> acceptTermination(@RequestParam Integer id, @RequestParam String acceptReasoning){
        User user = userService.findUserById(id);
        terminationReasoningService.updateTerminationReasoningByAnsweredStatus(user);
        userService.updateUserDeletedStatusById(id);
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendTerminationAcceptedEmail(userDTO, acceptReasoning);
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
