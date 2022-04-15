package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findUsersByActivatedStatus(Boolean isActivated, Boolean isDeleted){
        return userRepository.findUsersByActivatedStatus(isActivated, isDeleted);
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

    public User findUserById(Integer id){
        return userRepository.findUserById(id);
    }

    public void updateUserActivatedStatusById(Integer id){
        userRepository.updateUserActivatedStatusById(id);
    }

    public void updateUserDeletedStatusById(Integer id){
        userRepository.updateUserDeletedStatusById(id);
    }

    public RegistrationReasoning findRegistrationReasoningByUserId(User user){
        return userRepository.findRegistrationReasoningByUser(user);
    }

    public User registration(UserDTO userDTO) {
        return null;
    }

    private boolean validData(UserDTO userDTO) {
        if (userDTO.getEmail() == null || !userDTO.getEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            return false;
        }
        if (userDTO.getPassword() == null || userDTO.getConfirmPassword() == null || userDTO.getPassword() != userDTO.getConfirmPassword()) {
            return false;
        }
        return true;
    }
}
