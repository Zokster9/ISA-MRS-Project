package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.AddressDTO;
import com.example.projectmrsisa.dto.RegistrationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.AddressService;
import com.example.projectmrsisa.service.RegistrationReasoningService;
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
    private AddressService addressService;

    @Autowired
    private RegistrationReasoningService registrationReasoningService;

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
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        if (!validUser(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validAddress(userDTO.getAddressDTO())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        try {
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userDTO.getRegistrationType().equals("client")) {
            // TODO: ovde dodati kod za registraciju klijenta
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // ovo skloniti kad se odradi
        }else {
            if (userDTO.getPrivilegedUserType().equals("retreatOwner")) {
                try{
                    RetreatOwner retreatOwner = userService.addRetreatOwner(new RetreatOwner(userDTO, a));
                    RegistrationReasoning registrationReasoning = registrationReasoningService.addRegistrationReasoning(new RegistrationReasoning(retreatOwner, userDTO.getRegistrationExplanation()));
                    return new ResponseEntity<>(new UserDTO(retreatOwner, PrivilegedUser.RETREAT_OWNER, new RegistrationReasoningDTO(registrationReasoning)), HttpStatus.OK);
                }catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }else if (userDTO.getPrivilegedUserType().equals("shipOwner")) {
                try {
                    ShipOwner shipOwner = userService.addShipOwner(new ShipOwner(userDTO, a));
                    RegistrationReasoning registrationReasoning = registrationReasoningService.addRegistrationReasoning(new RegistrationReasoning(shipOwner, userDTO.getRegistrationExplanation()));
                    return new ResponseEntity<>(new UserDTO(shipOwner, PrivilegedUser.SHIP_OWNER, new RegistrationReasoningDTO(registrationReasoning)), HttpStatus.OK);
                }catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }else {
                // TODO: ovde dodati kod za registraciju instruktora pecanja
            }
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK); // ovo skloniti kada se odradi registracija za ostale role
    }

    private boolean validAddress(AddressDTO addressDTO) {
        if (addressDTO.getCountry().equals("") || addressDTO.getCountry() == null || !addressDTO.getCountry().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (addressDTO.getCity().equals("") || addressDTO.getCity() == null || !addressDTO.getCity().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        return !addressDTO.getStreet().equals("") && addressDTO.getStreet() != null;
    }

    private boolean validUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || !userDTO.getEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            return false;
        }
        if (userDTO.getPassword() == null || userDTO.getConfirmPassword() == null || !userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return false;
        }
        if (userDTO.getName() == null ||userDTO.getName().equals("") || !userDTO.getName().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (userDTO.getSurname() == null ||userDTO.getSurname().equals("") || !userDTO.getSurname().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().equals("") || !userDTO.getPhoneNumber().matches("^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
            return false;
        }
        if (!userDTO.getRegistrationType().equals("client") && !userDTO.getRegistrationType().equals("privilegedUser")) {
            return false;
        }
        if (userDTO.getRegistrationType().equals("privilegedUser")) {
            if (userDTO.getRegistrationExplanation() == null || userDTO.getRegistrationExplanation().equals("")) return false;
            if (!userDTO.getPrivilegedUserType().equals("retreatOwner") && !userDTO.getPrivilegedUserType().equals("shipOwner") && !userDTO.getPrivilegedUserType().equals("fishingInstructor")) {
                return false;
            }
        }
        return true;
    }

}
