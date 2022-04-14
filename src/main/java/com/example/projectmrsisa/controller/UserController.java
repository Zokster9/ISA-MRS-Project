package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.PrivilegedUser;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getInactiveUsers(){
        List<User> inactiveUsers = userService.findUsersByActivatedStatus(false);
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
            inactiveUsersDTO.add(new UserDTO(iu, privilegedUser));
        }
        return new ResponseEntity<>(inactiveUsersDTO, HttpStatus.OK);
    }
}
