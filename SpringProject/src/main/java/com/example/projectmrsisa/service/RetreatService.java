package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.repository.RetreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RetreatService {

    @Autowired
    private RetreatRepository retreatRepository;

    public Retreat addRetreat(Retreat retreat) {
        return retreatRepository.save(retreat);
    }
    
    public Retreat getRetreatById(Integer id) {
        return retreatRepository.getRetreatById(id);
    }

    public List<Retreat> getRetreats() {
        return retreatRepository.findAll();
    }

    public Retreat updateRetreat(Retreat retreat, RetreatDTO retreatDTO, Set<Tag> additionalServices) {
        if (!retreat.getName().equals(retreatDTO.getName())) retreat.setName(retreatDTO.getName());
        if (!retreat.getDescription().equals(retreatDTO.getDescription())) retreat.setDescription(retreatDTO.getDescription());
        if (retreat.getNumOfBeds() != retreatDTO.getNumOfBeds()) retreat.setNumOfBeds(retreatDTO.getNumOfBeds());
        if (retreat.getNumOfRooms() != retreatDTO.getNumOfRooms()) retreat.setNumOfRooms(retreatDTO.getNumOfRooms());
        if (retreat.getPrice() != retreatDTO.getPrice()) retreat.setPrice(retreatDTO.getPrice());
        if (retreat.getPictures().size() < retreatDTO.getPictures().size()) {
            for (String picture: retreatDTO.getPictures()) {
                if (!retreat.getPictures().contains(picture)) retreat.addPicture(picture);
            }
        }
        if (retreat.getRulesOfConduct().size() < retreatDTO.getRulesOfConduct().size()) {
            for (String ruleOfConduct: retreatDTO.getRulesOfConduct()) {
                if (!retreat.getRulesOfConduct().contains(ruleOfConduct)) retreat.addRuleOfConduct(ruleOfConduct);
            }
        }
        if (retreat.getAdditionalServices().size() < additionalServices.size()) {
            for (Tag additionalService: additionalServices) {
                if (!retreat.getAdditionalServices().contains(additionalService)) retreat.addAdditionalService(additionalService);
            }
        }
        return retreatRepository.save(retreat);
    }
}
