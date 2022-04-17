package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.FishingInstructor;
import com.example.projectmrsisa.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;

    public FishingInstructor findFishingInstructorByEmail(String email){
        return fishingInstructorRepository.findFishingInstructorByEmail(email);
    }


}
