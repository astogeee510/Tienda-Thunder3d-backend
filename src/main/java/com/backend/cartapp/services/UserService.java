package com.backend.cartapp.services;

import java.util.Optional;

import com.backend.cartapp.models.entities.User;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
