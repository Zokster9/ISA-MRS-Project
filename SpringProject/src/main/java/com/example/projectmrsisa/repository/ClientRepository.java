package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select c from Client c where c.id = ?1")
    Client findUserById(Integer id);
}
