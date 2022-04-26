package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.AddressDTO;
import com.example.projectmrsisa.dto.RegistrationReasoningDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
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

    @Autowired
    private TerminationReasoningService terminationReasoningService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/inactive", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getInactiveUsers(){
        List<User> inactiveUsers = userService.findUsersByActivatedStatus(false, false);
        List<UserDTO> inactiveUsersDTO = new ArrayList<UserDTO>();
        for (User iu : inactiveUsers){
            PrivilegedUser privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
            List<Role> userRoles = iu.getRoles();
            Role role = userRoles.get(0);
            switch (role.getName()) {
                case "ROLE_fishingInstructor":
                    privilegedUser = PrivilegedUser.FISHING_INSTRUCTOR;
                    break;
                case "ROLE_retreatOwner":
                    privilegedUser = PrivilegedUser.RETREAT_OWNER;
                    break;
                case "ROLE_shipOwner":
                    privilegedUser = PrivilegedUser.SHIP_OWNER;
                    break;
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

    @Transactional
    @PostMapping("/changeInfo")
    public ResponseEntity<UserDTO> changeInfo(@RequestBody UserDTO userDTO){
        if (!validChangedUserInfo(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!validAddress(userDTO.getAddressDTO())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        try {
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User u;
        try {
            u = userService.findUserByEmail(userDTO.getEmail());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!u.getName().equals(userDTO.getName())){
            userService.updateUserName(userDTO.getName(), userDTO.getEmail());
        }
        if (!u.getSurname().equals(userDTO.getSurname())){
            userService.updateUserSurname(userDTO.getSurname(), userDTO.getEmail());
        }
        if (!u.getAddress().getStreet().equals(userDTO.getAddressDTO().getStreet()) || !u.getAddress().getCity().equals(userDTO.getAddressDTO().getCity()) ||
            !u.getAddress().getCountry().equals(userDTO.getAddressDTO().getCountry())){
            userService.updateUserAddress(a, userDTO.getEmail());
        }
        if (!u.getPhoneNumber().equals(userDTO.getPhoneNumber())){
            userService.updateUserPhoneNumber(userDTO.getPhoneNumber(), userDTO.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        if (!validUser(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validAddress(userDTO.getAddressDTO())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Role role;
        System.out.println("Dosao sam ovde");
        try {
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
            System.out.println("Dosao sam ovde 1");
            role = roleService.findRoleByName("ROLE_" + userDTO.getPrivilegedUserType());
            System.out.println("Dosao sam ovde 2");
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userDTO.getRegistrationType().equals("client")) {
            // TODO: ovde dodati kod za registraciju klijenta
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // ovo skloniti kad se odradi
        }else {
            try{
                if (role == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                System.out.println("Dosao sam ovde 3");
                User user = userService.save(new User(userDTO, a, role));
                System.out.println("Dosao sam ovde 4");
                RegistrationReasoning registrationReasoning = registrationReasoningService.addRegistrationReasoning(new RegistrationReasoning(user, userDTO.getRegistrationExplanation()));
                return new ResponseEntity<>(new UserDTO(user, PrivilegedUser.RETREAT_OWNER, new RegistrationReasoningDTO(registrationReasoning)), HttpStatus.CREATED);
            }catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
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

    private boolean validChangedUserInfo(UserDTO userDTO){
        if (userDTO.getName() == null ||userDTO.getName().equals("") || !userDTO.getName().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (userDTO.getSurname() == null ||userDTO.getSurname().equals("") || !userDTO.getSurname().matches("([A-Z]{1})([a-z]+)([^0-9]*)$")) {
            return false;
        }
        if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().equals("") || !userDTO.getPhoneNumber().matches("^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
            return false;
        }
        return true;
    }

    @GetMapping(value="/findByEmail/{email}")
    //TODO: Autorizacija
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    //TODO: Mozda ipak zaseban Controller za svakog korisnika
    @Transactional
    @PostMapping(value="/sendTerminationReason")
    //TODO: Autorizacija
    public ResponseEntity<UserDTO> sendTerminationReason(@RequestParam String email, @RequestParam String terminationReason){
        User user = userService.findUserByEmail(email);
        TerminationReasoning terminationReasoning = terminationReasoningService.addTerminationReasoning(new TerminationReasoning(user, terminationReason));
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/changePassword")
    public ResponseEntity<UserDTO> changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword){
        User user = userService.findUserByEmail(email);
        if (!user.getPassword().equals(oldPassword)){
            UserDTO userDTO = new UserDTO(user);
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        userService.updateUserPassword(newPassword, email);
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}