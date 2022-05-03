package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ShipDTO;
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

    public Ship updateRetreat(Ship ship, ShipDTO shipDTO) {
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
        if (ship.getPictures().size() < shipDTO.getPictures().size()) {
            for (String picture: shipDTO.getPictures()) {
                if (!ship.getPictures().contains(picture)) ship.addPicture(picture);
            }
        }
        if (ship.getFishingEquipment().size() < shipDTO.getFishingEquipment().size()) {
            for (String fishEq: shipDTO.getFishingEquipment()) {
                if (!ship.getFishingEquipment().contains(fishEq)) ship.addFishingEquipment(fishEq);
            }
        }
        if (ship.getRulesOfConduct().size() < shipDTO.getRulesOfConduct().size()) {
            for (String ruleOfConduct: shipDTO.getRulesOfConduct()) {
                if (!ship.getRulesOfConduct().contains(ruleOfConduct)) ship.addRuleOfConduct(ruleOfConduct);
            }
        }
        if (ship.getNavigationEquipment().size() < shipDTO.getNavigationEquipment().size()) {
            for (String navEq: shipDTO.getNavigationEquipment()) {
                if (!ship.getNavigationEquipment().contains(navEq)) ship.addNavigationEquipment(navEq);
            }
        }
        return shipRepository.save(ship);
    }

    public void deleteShipById(Integer id) {
        shipRepository.deleteShipById(id);
    }
}
