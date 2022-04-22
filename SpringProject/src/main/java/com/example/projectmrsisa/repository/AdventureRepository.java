package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
}
