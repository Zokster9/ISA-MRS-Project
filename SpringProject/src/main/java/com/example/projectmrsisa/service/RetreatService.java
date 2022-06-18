package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.model.Action;
import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.repository.RetreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RetreatService {

    private final Logger LOG = LoggerFactory.getLogger(RetreatService.class);

    @Autowired
    private RetreatRepository retreatRepository;

    public Retreat addRetreat(Retreat retreat) {
        return retreatRepository.save(retreat);
    }

    @Cacheable("retreat")
    public Retreat getRetreatById(Integer id) {
        LOG.info("Retreat with id: " + id + " successfully cached!");
        return retreatRepository.getRetreatById(id);
    }

    public List<Retreat> getRetreats() {
        return retreatRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Retreat updateRetreat(Retreat retreat, RetreatDTO retreatDTO, Set<Tag> additionalServices) {
        if (!retreat.getName().equals(retreatDTO.getName())) retreat.setName(retreatDTO.getName());
        if (!retreat.getDescription().equals(retreatDTO.getDescription())) retreat.setDescription(retreatDTO.getDescription());
        if (retreat.getNumOfBeds() != retreatDTO.getNumOfBeds()) retreat.setNumOfBeds(retreatDTO.getNumOfBeds());
        if (retreat.getNumOfRooms() != retreatDTO.getNumOfRooms()) retreat.setNumOfRooms(retreatDTO.getNumOfRooms());
        if (retreat.getPrice() != retreatDTO.getPrice()) retreat.setPrice(retreatDTO.getPrice());
        if (retreat.getPictures().size() != retreatDTO.getPictures().size()) {
            retreat.setPictures(new HashSet<>(retreatDTO.getPictures()));
        }
        if (retreat.getRulesOfConduct().size() != retreatDTO.getRulesOfConduct().size()) {
            retreat.setRulesOfConduct(new HashSet<>(retreatDTO.getRulesOfConduct()));
        }
        if (retreat.getAdditionalServices().size() != additionalServices.size()) {
            retreat.setAdditionalServices(additionalServices);
        }
        return retreatRepository.save(retreat);
    }

    @Transactional(readOnly = false)
    public void deleteRetreat(Integer id) {
        retreatRepository.deleteRetreatById(id);
    }

    @Transactional(readOnly = false)
    public Retreat addAction(Retreat retreat, Action action) {
        retreat.addAction(action);
        return retreatRepository.save(retreat);
    }

    @CacheEvict(cacheNames = {"retreat"}, allEntries = true)
    public void removeFromCache() {
        LOG.info("Retreats removed from cache!");
    }
}
