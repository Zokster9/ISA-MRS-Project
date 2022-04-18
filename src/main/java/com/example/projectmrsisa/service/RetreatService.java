package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Retreat;
import com.example.projectmrsisa.repository.RetreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetreatService {

    @Autowired
    private RetreatRepository retreatRepository;

    public Retreat getRetreatById(Integer id) {
        return retreatRepository.getRetreatById(id);
    }
}
