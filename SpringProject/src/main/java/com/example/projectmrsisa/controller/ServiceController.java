package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ServiceDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.ServiceService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Transactional
    @DeleteMapping(value="/delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin', 'fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity deleteService(@PathVariable Integer id){
        try {
            serviceService.deleteServiceById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/allSubscriptions")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ServiceDTO>> getAllSubscriptions(Principal principal) {
        Client client = (Client) userService.findUserByEmail(principal.getName());
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        for (Service service: client.getSubscriptions())
            serviceDTOs.add(new ServiceDTO(service));
        return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
    }
}
