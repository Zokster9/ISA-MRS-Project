package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.example.projectmrsisa.model.Service> findOwnersServices(User user){
        return serviceRepository.findOwnersServices(user);
    }
}
