package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.repository.FishingInstructorRepository;
import com.example.projectmrsisa.repository.RetreatOwnerRepository;
import com.example.projectmrsisa.repository.ShipOwnerRepository;
import com.example.projectmrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RetreatOwnerRepository retreatOwnerRepository;

    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;

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

    public RegistrationReasoning findRegistrationReasoningByUserId(User user) {
        return userRepository.findRegistrationReasoningByUser(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public void updateUserPassword(String password, String email){
        userRepository.updateUserPassword(password, email);
    }
    public RetreatOwner getRetreatOwnerById(int id) {
        return retreatOwnerRepository.getById(id);
    }

    public ShipOwner getOwnerById(int id) {
        return shipOwnerRepository.getById(id);
    }
    public RetreatOwner addRetreatOwner(RetreatOwner retreatOwner) {
        return retreatOwnerRepository.save(retreatOwner);
    }
    public FishingInstructor addFishingInstructor(FishingInstructor fishingInstructor) { return fishingInstructorRepository.save(fishingInstructor);}

    public ShipOwner addShipOwner(ShipOwner shipOwner) {
        return shipOwnerRepository.save(shipOwner);
    }

    public FishingInstructor findFishingInstructorByEmail(String email){
        return fishingInstructorRepository.findFishingInstructorByEmail(email);
    }

    public void updateFishingInstructorDeletedStatusByEmail(boolean isDeleted, String email){
        fishingInstructorRepository.updateFishingInstructorDeletedStatusByEmail(isDeleted, email);
    }

    public void updateUserName(String name, String email){
        userRepository.updateUserName(name, email);
    }

    public void updateUserSurname(String surname, String email){
        userRepository.updateUserSurname(surname, email);
    }

    public void updateUserAddress(Address address, String email){
        userRepository.updateUserAddress(address, email);
    }

    public void updateUserPhoneNumber(String phoneNumber, String email){
        userRepository.updateUserPhoneNumber(phoneNumber, email);
    }
}
