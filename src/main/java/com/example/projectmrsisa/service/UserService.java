package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.FishingInstructor;
import com.example.projectmrsisa.model.RetreatOwner;
import com.example.projectmrsisa.model.ShipOwner;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findUsersByActivatedStatus(Boolean isActivated){
        return userRepository.findUsersByActivatedStatus(isActivated);
    }

    public FishingInstructor findFishingInstructorById(Integer id){
        return userRepository.findFishingInstructorById(id);
    }

    public ShipOwner findShipOwnerById(Integer id){
        return userRepository.findShipOwnerById(id);
    }

    public RetreatOwner findRetreatOwnerById(Integer id){
        return userRepository.findRetreatOwnerById(id);
    }
}
