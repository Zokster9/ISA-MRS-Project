package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
