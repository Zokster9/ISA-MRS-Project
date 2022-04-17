package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.ShipOwner;
import com.example.projectmrsisa.repository.ShipOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    public ShipOwner getOwnerById(int id) {
        return shipOwnerRepository.getById(id);
    }
}
