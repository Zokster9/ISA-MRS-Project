package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.RetreatOwner;
import com.example.projectmrsisa.repository.RetreatOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RetreatOwnerRepository retreatOwnerRepository;

    public RetreatOwner getRetreatOwnerById(int id) {
        return retreatOwnerRepository.getById(id);
    }
}
