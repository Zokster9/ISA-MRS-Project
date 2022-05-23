package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Rating;
import com.example.projectmrsisa.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
