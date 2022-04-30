package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Admin;
import com.example.projectmrsisa.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public void updatePasswordChangedStatus(Integer id){
        adminRepository.updatePasswordChangedStatus(id);
    }

    public Admin findAdminByUserId(Integer id){
        return adminRepository.findAdminByUserId(id);
    }
}
