package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.LoyaltyProgramDTO;
import com.example.projectmrsisa.model.LoyaltyProgram;
import com.example.projectmrsisa.model.LoyaltyStatus;
import com.example.projectmrsisa.service.LoyaltyProgramService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/loyaltyPrograms")
public class LoyaltyProgramController {

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private UserService userService;

    @PostMapping(value="/add")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<LoyaltyProgramDTO> addNewLoyaltyProgram(@RequestBody LoyaltyProgramDTO loyaltyProgramDTO){
        LoyaltyProgram loyaltyProgram;
        try{
            loyaltyProgramService.finishPreviousLoyaltyProgram();
            loyaltyProgramDTO.setClientBonusSilver(loyaltyProgramDTO.getClientBonusSilver() * 0.01);
            loyaltyProgramDTO.setClientBonusGold(loyaltyProgramDTO.getClientBonusGold() * 0.01);
            loyaltyProgramDTO.setPrivilegedUserBonusSilver(loyaltyProgramDTO.getPrivilegedUserBonusSilver() * 0.01);
            loyaltyProgramDTO.setPrivilegedUserBonusGold(loyaltyProgramDTO.getPrivilegedUserBonusGold() * 0.01);
            loyaltyProgram = loyaltyProgramService.addNewLoyaltyProgram(new LoyaltyProgram(loyaltyProgramDTO));
            userService.resetLoyaltyPoints();
            userService.resetLoyaltyStatus(LoyaltyStatus.Regular);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoyaltyProgramDTO loyaltyProgramDTO1 = new LoyaltyProgramDTO(loyaltyProgram);
        return new ResponseEntity<>(loyaltyProgramDTO1, HttpStatus.OK);
    }
}
