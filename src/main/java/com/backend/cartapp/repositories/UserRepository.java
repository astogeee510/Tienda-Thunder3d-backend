package com.backend.cartapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.backend.cartapp.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
