package com.backend.cartapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.backend.cartapp.models.entities.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
