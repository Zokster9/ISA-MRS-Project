package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ClientDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.ClientService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value= "/clients")
public class ClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value="/getLoggedClient")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ClientDTO> getLoggedClient(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Client client = clientService.findClientById(user.getId());
        ClientDTO clientDTO = new ClientDTO(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}
