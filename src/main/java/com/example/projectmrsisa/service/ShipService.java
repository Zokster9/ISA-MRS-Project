package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public Ship addShip(Ship ship) {
        return shipRepository.save(ship);
    }
}
