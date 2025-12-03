package com.backend.cartapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.backend.cartapp.models.dto.AuthRequest;
import com.backend.cartapp.models.dto.AuthResponse;
import com.backend.cartapp.models.dto.RegisterRequest;
import com.backend.cartapp.models.entities.User;
import com.backend.cartapp.security.JwtTokenUtil;
import com.backend.cartapp.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    private static final String ADMIN_EMAIL = "admin@thunder3d.cl";
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario registrado con ese email");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        User saved = userService.save(user);

        String token = jwtTokenUtil.generateToken(saved.getEmail());
        AuthResponse response = new AuthResponse(token, saved.getEmail(), saved.getRole());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();


        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {

            String token = jwtTokenUtil.generateToken(email);
            AuthResponse response = new AuthResponse(token, email, "ADMIN");

            return ResponseEntity.ok(response);
        }

        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                AuthResponse response = new AuthResponse(token, user.getEmail(), user.getRole());
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales inválidas para el usuario");
    }
}
