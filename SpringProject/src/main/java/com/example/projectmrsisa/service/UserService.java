package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
