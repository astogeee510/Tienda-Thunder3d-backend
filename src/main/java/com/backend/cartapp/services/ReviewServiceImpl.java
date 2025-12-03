package com.backend.cartapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.cartapp.models.entities.Review;
import com.backend.cartapp.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return (List<Review>) repository.findAll();
    }

    @Override
    @Transactional
    public Review save(Review review) {
        return repository.save(review);
    }
}
