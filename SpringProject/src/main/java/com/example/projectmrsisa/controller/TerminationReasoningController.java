package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.TerminationChoiceDTO;
import com.example.projectmrsisa.dto.TerminationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.ServiceService;
import com.example.projectmrsisa.service.TerminationReasoningService;
import com.example.projectmrsisa.service.UserService;
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
@RequestMapping(value="termination")
public class TerminationReasoningController {

    @Autowired
    private TerminationReasoningService terminationReasoningService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping(value="/findToTerminate", produces="application/json")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<List<TerminationReasoningDTO>> findToBeTerminatedUsers(){
        List<TerminationReasoning> unansweredTerminationReasonings = terminationReasoningService.findUnansweredTerminationReasonings();
        List<TerminationReasoningDTO>  trDTO = new ArrayList<TerminationReasoningDTO>();
        for (TerminationReasoning tr : unansweredTerminationReasonings){
            trDTO.add(new TerminationReasoningDTO(tr));
        }
        return new ResponseEntity<>(trDTO, HttpStatus.OK);
    }

    @PostMapping(value="/declineTermination")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> declineTermination(@RequestBody TerminationChoiceDTO terminationChoiceDTO){
        User user;
        //LOCK
        try {
            user = userService.findUserById(terminationChoiceDTO.getUserId());
        } catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        terminationReasoningService.updateTerminationReasoningByAnsweredStatus(user);
        userService.updateUserActivatedStatusById(terminationChoiceDTO.getUserId()); // aktiviram korisnika opet
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendTerminationDeclinedEmail(userDTO, terminationChoiceDTO.getTerminationChoiceReason());
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/acceptTermination")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> acceptTermination(@RequestBody TerminationChoiceDTO terminationChoiceDTO){
        User user;
        //LOCK
        try{
            user = userService.findUserById(terminationChoiceDTO.getUserId());
        } catch (PessimisticLockingFailureException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        terminationReasoningService.updateTerminationReasoningByAnsweredStatus(user);
        userService.updateUserDeletedStatusById(terminationChoiceDTO.getUserId());
        serviceService.deleteServicesByOwner(user);
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendTerminationAcceptedEmail(userDTO, terminationChoiceDTO.getTerminationChoiceReason());
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
