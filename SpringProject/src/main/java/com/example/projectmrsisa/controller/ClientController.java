package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ClientDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.Service;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.service.ClientService;
import com.example.projectmrsisa.service.ServiceService;
import com.example.projectmrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value= "/clients")
public class ClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping(value="/getLoggedClient")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ClientDTO> getLoggedClient(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Client client = clientService.findClientById(user.getId());
        ClientDTO clientDTO = new ClientDTO(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PutMapping(value="/subscribe/{id}")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ClientDTO> subscribe(Principal principal, @PathVariable Integer id) {
        User user = userService.findUserByEmail(principal.getName());
        Client client = clientService.findClientById(user.getId());
        Service service = serviceService.findById(id);
        client.getSubscriptions().add(service);
        client = clientService.save(client);
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }

    @PutMapping(value="/unsubscribe/{id}")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ClientDTO> unsubscribe(Principal principal, @PathVariable Integer id) {
        User user = userService.findUserByEmail(principal.getName());
        Client client = clientService.findClientById(user.getId());
        Service service = serviceService.findById(id);
        client.getSubscriptions().remove(service);
        client = clientService.save(client);
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }
}
