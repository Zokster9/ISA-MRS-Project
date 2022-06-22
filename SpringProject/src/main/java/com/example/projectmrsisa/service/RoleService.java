package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Role;
import com.example.projectmrsisa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        Role auth = this.roleRepository.getOne(id);
        return auth;
    }

    public List<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Role findRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }
}
