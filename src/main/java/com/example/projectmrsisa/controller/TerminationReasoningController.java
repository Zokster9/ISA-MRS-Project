package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.TerminationReasoningDTO;
import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.service.TerminationReasoningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="termination")
public class TerminationReasoningController {

    @Autowired
    private TerminationReasoningService terminationReasoningService;

    @GetMapping(value="findToTerminate", produces="application/json")
    public ResponseEntity<List<TerminationReasoningDTO>> findToBeTerminatedUsers(){
        //TODO: JWT
        List<TerminationReasoning> unansweredTerminationReasonings = terminationReasoningService.findUnansweredTerminationReasonings();
        List<TerminationReasoningDTO>  trDTO = new ArrayList<TerminationReasoningDTO>();
        for (TerminationReasoning tr : unansweredTerminationReasonings){
            trDTO.add(new TerminationReasoningDTO(tr));
        }
        return new ResponseEntity<>(trDTO, HttpStatus.OK);
    }

    @PostMapping(value="declineTermination")
    public ResponseEntity<TerminationReasoningDTO> declineTermination(){
        return null;
    }

    @PostMapping(value="acceptTermination")
    public ResponseEntity<TerminationReasoningDTO> acceptTermination(){
        return null;
    }
}
