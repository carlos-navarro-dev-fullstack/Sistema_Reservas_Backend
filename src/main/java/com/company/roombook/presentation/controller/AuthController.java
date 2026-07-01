package com.company.roombook.presentation.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.roombook.application.usecase.AuthService;
import com.company.roombook.presentation.dto.request.LoginRequest;
import com.company.roombook.presentation.dto.request.RegisterRequest;
import com.company.roombook.presentation.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        log.info("Intento de registro para usuario: {}", request.getEmail());

        authService.register(request);

        log.info("Registro exitoso para usuario: {}", request.getEmail());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        log.info("Intento de login para usuario: {}", request.getEmail());

        AuthResponse response = authService.login(request);

        log.info("Login exitoso para usuario: {}", request.getEmail());

        return response;
    }
}