package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.TerminationReasoning;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.TerminationReasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TerminationReasoningService {

    @Autowired
    TerminationReasoningRepository terminationReasoningRepository;

    public TerminationReasoning addTerminationReasoning(TerminationReasoning terminationReasoning){
        return terminationReasoningRepository.save(terminationReasoning);
    }

    public List<TerminationReasoning> findUnansweredTerminationReasonings(){
        return terminationReasoningRepository.findUnansweredTerminationReasonings();
    }

    public void updateTerminationReasoningByAnsweredStatus(User user){
        terminationReasoningRepository.updateTerminationReasoningByAnsweredStatus(user);
    }
}
