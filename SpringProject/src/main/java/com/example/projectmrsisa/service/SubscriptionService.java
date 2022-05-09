package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    public List<String> findClientsWithSubscription(List<Client> clients, Integer serviceId) {
        List<String> emails = new ArrayList<>();
        for (Client client: clients) {
            if (client.hasSubscription(serviceId)) emails.add(client.getEmail());
        }
        return emails;
    }
}
