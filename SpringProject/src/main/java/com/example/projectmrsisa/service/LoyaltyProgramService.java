package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.LoyaltyProgram;
import com.example.projectmrsisa.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoyaltyProgramService {

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Transactional(readOnly = false)
    public void finishPreviousLoyaltyProgram(){
        loyaltyProgramRepository.finishPreviousLoyaltyProgram();
    }

    public LoyaltyProgram addNewLoyaltyProgram(LoyaltyProgram loyaltyProgram){
        return loyaltyProgramRepository.save(loyaltyProgram);
    }

    public LoyaltyProgram findActiveLoyaltyProgram(){
        return loyaltyProgramRepository.findActiveLoyaltyProgram();
    }
}
