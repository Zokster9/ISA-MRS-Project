package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.repository.RetreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
