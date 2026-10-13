package com.example.tourist;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if ("admin".equals(username)
                && "admin123".equals(password)) {

            String token =
                    jwtService.generateToken(username);

            return ResponseEntity.ok(
                    Map.of("token", token));
        }

        return ResponseEntity.status(401)
                .body(Map.of("message",
                        "Invalid username or password"));
    }
}