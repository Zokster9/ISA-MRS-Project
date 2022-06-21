package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ShipDTO;
import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Transactional(readOnly = false)
    public Ship addShip(Ship ship) {
        return shipRepository.save(ship);
    }

    public List<Ship> getShips() { return shipRepository.findAll(); }

    public Ship findShipById(int id) {
        return shipRepository.findShipById(id);
    }

    @Transactional(readOnly = false)
    public Ship updateShip(Ship ship, ShipDTO shipDTO, Set<Tag> additionalServices) {
        if (!ship.getName().equals(shipDTO.getName())) ship.setName(shipDTO.getName());
        if (!ship.getDescription().equals(shipDTO.getDescription())) ship.setDescription(shipDTO.getDescription());
        if (ship.getCapacity() != shipDTO.getCapacity()) ship.setCapacity(shipDTO.getCapacity());
        if (!ship.getEngineNum().equals(shipDTO.getEngineNum())) ship.setEngineNum(shipDTO.getEngineNum());
        if (ship.getPrice() != shipDTO.getPrice()) ship.setPrice(shipDTO.getPrice());
        if (ship.getEnginePower() != shipDTO.getEnginePower()) ship.setEnginePower(shipDTO.getEnginePower());
        if (ship.getLength() != shipDTO.getLength()) ship.setLength(shipDTO.getLength());
        if (!ship.getMaxSpeed().equals(shipDTO.getMaxSpeed())) ship.setMaxSpeed(shipDTO.getMaxSpeed());
        if (!ship.getReservationCancellationConditions().equals(shipDTO.getReservationCancellationConditions())) ship.setReservationCancellationConditions(shipDTO.getReservationCancellationConditions());
        if (!ship.getType().equals(shipDTO.getType())) ship.setType(shipDTO.getType());
        ship.setPictures(new HashSet<>(shipDTO.getPictures()));
        ship.setFishingEquipment(new HashSet<>(shipDTO.getFishingEquipment()));
        ship.setRulesOfConduct(new HashSet<>(shipDTO.getRulesOfConduct()));
        ship.setNavigationEquipment(new HashSet<>(shipDTO.getNavigationEquipment()));
        ship.setAdditionalServices(additionalServices);
        return shipRepository.save(ship);
    }

    @Transactional(readOnly = false)
    public void deleteShipById(Integer id) {
        shipRepository.deleteShipById(id);
    }

    @Transactional(readOnly = false)
    public Ship addAction(Ship ship, Action action) {
        ship.addAction(action);
        return shipRepository.save(ship);
    }
}
