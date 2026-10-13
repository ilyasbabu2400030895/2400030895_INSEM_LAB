package com.example.tourist;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final TouristRepository repository;
    private final JwtService jwtService;

    public TouristController(
            TouristRepository repository,
            JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTourists(
            @RequestHeader(
                    value = "Authorization",
                    required = false)
            String authorization) {

        if (authorization == null
                || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(401)
                    .body("Missing token");
        }

        String token = authorization.substring(7);

        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }

        List<Tourist> tourists = repository.findAll();

        return ResponseEntity.ok(tourists);
    }
}