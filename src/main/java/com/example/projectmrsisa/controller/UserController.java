package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.RegistrationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.PrivilegedUser;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value="/inactive", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getInactiveUsers(){
        List<User> inactiveUsers = userService.findUsersByActivatedStatus(false, false);
        List<UserDTO> inactiveUsersDTO = new ArrayList<UserDTO>();
        for (User iu : inactiveUsers){
            PrivilegedUser privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
            if (userService.findFishingInstructorById(iu.getId()) != null ){
                privilegedUser = PrivilegedUser.FISHING_INSTRUCTOR;
            }
            else if (userService.findRetreatOwnerById(iu.getId()) != null){
                privilegedUser = PrivilegedUser.RETREAT_OWNER;
            }
            else if (userService.findShipOwnerById(iu.getId()) != null){
                privilegedUser = PrivilegedUser.SHIP_OWNER;
            }
            RegistrationReasoningDTO registrationReasoningDTO = new RegistrationReasoningDTO(userService.findRegistrationReasoningByUserId(iu));
            inactiveUsersDTO.add(new UserDTO(iu, privilegedUser, registrationReasoningDTO));
        }
        return new ResponseEntity<>(inactiveUsersDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/accept/{id}")
    public ResponseEntity<UserDTO> acceptUser(@PathVariable Integer id){
        // TODO: Autentifikacija
        User user = userService.findUserById(id);
        userService.updateUserActivatedStatusById(user.getId());
        UserDTO userDTO = new UserDTO(user);
        try{
            emailService.sendRegistrationAcceptedEmail(userDTO);
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/decline")
    public ResponseEntity<UserDTO> declineUser(@RequestParam Integer id, @RequestParam String declineReasoning){
        // TODO: Autentifikacija
        User user = userService.findUserById(id);
        userService.updateUserDeletedStatusById(id);
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendRegistrationDeclinedEmail(userDTO, declineReasoning);
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
