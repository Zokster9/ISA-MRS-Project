package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.AdventureDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdventureService {

    List<AdventureDTO> adventures = new ArrayList<AdventureDTO>();

    public AdventureDTO save(AdventureDTO adventure){
        adventures.add(adventure);
        return adventure;
    }

    /*public Adventure save(Adventure adventure){
        return adventureRepository.save(adventure); //dodaj repository
    }    */
}
