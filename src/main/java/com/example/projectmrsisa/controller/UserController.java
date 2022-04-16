package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.AddressDTO;
import com.example.projectmrsisa.dto.FishingInstructorDTO;
import com.example.projectmrsisa.dto.RegistrationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.FishingInstructor;
import com.example.projectmrsisa.model.PrivilegedUser;
import com.example.projectmrsisa.model.User;
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
        User user = userService.findUserById(id);
        userService.updateUserActivatedStatusById(user.getId());
        UserDTO userDTO = new UserDTO(user);
        // TODO: Poslati email
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/decline")
    public ResponseEntity<UserDTO> declineUser(@RequestParam Integer id, @RequestParam String declineReasoning){
        System.out.println(declineReasoning);
        User user = userService.findUserById(id);
        userService.updateUserDeletedStatusById(id);
        UserDTO userDTO = new UserDTO(user);
        //TODO: Razlog brisanja, poslati email
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value="/findByEmail/{email}")
    //TODO: Autorizacija
    public ResponseEntity<FishingInstructorDTO> getFishingInstructor(@PathVariable String email){
        FishingInstructor fishingInstructor = userService.findFishingInstructorByEmail(email);
        FishingInstructorDTO fishingInstructorDTO = new FishingInstructorDTO(fishingInstructor);
        return new ResponseEntity<>(fishingInstructorDTO, HttpStatus.OK);
    }
    //TODO: Mozda ipak zaseban Controller za svakog korisnika
    @Transactional
    @PostMapping(value="/deleteByEmail/{email}")
    //TODO: Autorizacija
    public ResponseEntity<FishingInstructorDTO> deleteFishingInstructor(@PathVariable String email){
        userService.updateFishingInstructorDeletedStatusByEmail(true, email);
        FishingInstructorDTO fishingInstructorDTO = new FishingInstructorDTO(userService.findFishingInstructorByEmail(email));
        return new ResponseEntity<>(fishingInstructorDTO, HttpStatus.OK);
    }
}
