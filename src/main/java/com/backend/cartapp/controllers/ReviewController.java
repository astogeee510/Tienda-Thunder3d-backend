package com.backend.cartapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cartapp.models.dto.ReviewRequest;
import com.backend.cartapp.models.entities.Review;
import com.backend.cartapp.services.ReviewService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8081"})
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping("/reviews")
    public List<Review> list() {
        return service.findAll();
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> create(@RequestBody ReviewRequest request) {
        try {
            Review r = new Review();
            r.setName(request.getName());
            r.setComment(request.getComment());
            r.setRating(request.getRating());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(r));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
