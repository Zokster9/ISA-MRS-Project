package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client save(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateClientsPenaltyStatus() {
        clientRepository.updateClientsPenaltyStatus();
    }
}
