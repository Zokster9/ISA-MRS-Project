package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.AdventureDTO;
import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Adventure;
import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Transactional
@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure addAdventure(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    public Adventure findAdventureById(Integer id) { return adventureRepository.findAdventureById(id); }

    @Transactional(readOnly = false)
    public void deleteAdventureById(Integer id) { adventureRepository.deleteAdventureById(id); }

    @Transactional(readOnly = false)
    public Adventure updateAdventure(Adventure adventure, AdventureDTO adventureDTO, Set<Tag> additionalServices) {
        if (!adventure.getName().equals(adventureDTO.getName())) adventure.setName(adventureDTO.getName());
        if (!adventure.getDescription().equals(adventureDTO.getDescription()))
            adventure.setDescription(adventureDTO.getDescription());
        if (adventure.getMaxNumOfPeople() != adventureDTO.getMaxNumOfPeople())
            adventure.setMaxNumOfPeople(adventureDTO.getMaxNumOfPeople());
        if (!adventure.getInstructorBiography().equals(adventureDTO.getInstructorBiography()))
            adventure.setInstructorBiography(adventureDTO.getInstructorBiography());
        if (adventure.getPrice() != adventureDTO.getPrice()) adventure.setPrice(adventureDTO.getPrice());
        if (!adventure.getReservationCancellationConditions().equals(adventureDTO.getReservationCancellationConditions()))
            adventure.setReservationCancellationConditions(adventureDTO.getReservationCancellationConditions());
        adventure.setPictures(new HashSet<>(adventureDTO.getPictures()));
        adventure.setRulesOfConduct(new HashSet<>(adventureDTO.getRulesOfConduct()));
        adventure.setFishingEquipment(new HashSet<>(adventureDTO.getFishingEquipment()));
        adventure.setAdditionalServices(additionalServices);
        return adventureRepository.save(adventure);
    }

    public Adventure addAction(Adventure adventure, Action action){
        adventure.addAction(action);
        return adventureRepository.save(adventure);
    }

    public List<Adventure> getAdventures() { return adventureRepository.findAll(); }

    public List<Adventure> findOwnersAdventures(Integer ownerId){
        return adventureRepository.findOwnersAdventures(ownerId);
    }
}
