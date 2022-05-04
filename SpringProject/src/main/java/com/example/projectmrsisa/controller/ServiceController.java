package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Transactional
    @DeleteMapping(value="delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin', 'fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity deleteService(@PathVariable Integer id){
        try {
            serviceService.deleteServiceById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
