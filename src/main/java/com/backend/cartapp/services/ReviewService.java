package com.backend.cartapp.services;

import java.util.List;

import com.backend.cartapp.models.entities.Review;

public interface ReviewService {

    List<Review> findAll();

    Review save(Review review);
}
