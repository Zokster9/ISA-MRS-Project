package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findUsersByActivatedStatus(Boolean isActivated, Boolean isDeleted){
        return userRepository.findUsersByActivatedStatus(isActivated, isDeleted);
    }

    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public User findUserById(Integer id){
        return userRepository.findUserById(id);
    }

    public void updateUserActivatedStatusById(Integer id){
        userRepository.updateUserActivatedStatusById(id);
    }

    public void deactivateUserById(Integer id){
        userRepository.deactivateUserById(id);
    }

    public void updateUserDeletedStatusById(Integer id){
        userRepository.updateUserDeletedStatusById(id);
    }

    public RegistrationReasoning findRegistrationReasoningByUserId(Integer id) {
        return userRepository.findRegistrationReasoningByUserId(id);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public void updateUserPassword(String password, String email){
        userRepository.updateUserPassword(passwordEncoder.encode(password), email);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void updateUserDeletedStatusByEmail(boolean isDeleted, String email){
        userRepository.updateUserDeletedStatusByEmail(isDeleted, email);
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
    public void updateRegistrationReasoningStatus(User user){
        userRepository.updateRegistrationReasoningAnsweredStatus(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void resetLoyaltyPoints(){
        userRepository.resetLoyaltyPoints();
    }

    public void resetLoyaltyStatus(LoyaltyStatus status){
        userRepository.resetLoyaltyStatus(status);
    }

    public List<User> getAllInstructors(String role) {
        return userRepository.getAllInstructors(role);
    }
}
