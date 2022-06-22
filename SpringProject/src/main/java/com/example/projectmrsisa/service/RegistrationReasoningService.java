package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.RegistrationReasoning;
import com.example.projectmrsisa.repository.RegistrationReasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationReasoningService {

    @Autowired
    private RegistrationReasoningRepository registrationReasoningRepository;

    public RegistrationReasoning addRegistrationReasoning(RegistrationReasoning registrationReasoning) {
        return registrationReasoningRepository.save(registrationReasoning);
    }
}
