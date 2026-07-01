package com.company.roombook.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping
    public Map<String, Object> home() {

        log.info("Petición recibida en el endpoint principal.");

        return Map.of(
                "message", "Bienvenido a RoomBook",
                "status", "OK",
                "timestamp", LocalDateTime.now()
        );
    }

    @GetMapping("/health-check")
    public Map<String, Object> healthCheck() {

        log.info("Health check ejecutado.");

        return Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now()
        );
    }

    @GetMapping("/error-test")
    public String errorTest() {

        log.error("Generando un error de prueba.");

        throw new RuntimeException("Error de prueba para Loki.");
    }
}