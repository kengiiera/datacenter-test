package com.recharges.recharge_backend.infrastructure.rest.controller;

import com.recharges.recharge_backend.infrastructure.persistence.entities.UserEntity;
import com.recharges.recharge_backend.infrastructure.persistence.repository.UserRepository;
import com.recharges.recharge_backend.infrastructure.security.jwt.JwtService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar autenticación (login).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint para login.
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var userOpt = userRepository.findById(request.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no existe");
        }

        UserEntity user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        // Generar JWT
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    /**
     * DTO para recibir las credenciales de login.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
    }

    /**
     * DTO para responder con el token JWT.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
    }
}
