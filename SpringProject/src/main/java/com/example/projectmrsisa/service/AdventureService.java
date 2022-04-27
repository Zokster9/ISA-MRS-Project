package com.example.projectmrsisa.service;
import com.example.projectmrsisa.model.Adventure;
import com.example.projectmrsisa.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure addAdventure(Adventure adventure){
        return adventureRepository.save(adventure);
    }

    public Adventure findAdventureById(Integer id) { return adventureRepository.findAdventureById(id); }
}
