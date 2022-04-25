package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public Ship addShip(Ship ship) {
        return shipRepository.save(ship);
    }

    public List<Ship> getShips() { return shipRepository.findAll(); }

    public Ship findShipById(int id) {
        return shipRepository.findShipById(id);
    }
}
