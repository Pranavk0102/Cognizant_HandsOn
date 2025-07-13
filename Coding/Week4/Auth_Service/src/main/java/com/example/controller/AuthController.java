package com.example.controller;

import com.example.dto.TokenResponse;
import com.example.exception.AuthenticationException;
import com.example.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        try {
            // Extract credentials from Authorization header
            String[] credentials = extractCredentials(request);
            String username = credentials[0];
            String password = credentials[1];

            // Validate credentials (in real app, use UserDetailsService)
            if (isValidCredentials(username, password)) {
                // Generate JWT token
                String token = jwtService.generateToken(username);
                return ResponseEntity.ok(new TokenResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("{\"error\":\"Invalid credentials\"}");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"Authentication failed: " + e.getMessage() + "\"}");
        }
    }

    private String[] extractCredentials(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new AuthenticationException("Missing or invalid Authorization header");
        }

        // Extract Base64 encoded credentials
        String base64Credentials = authHeader.substring(6);
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                StandardCharsets.UTF_8);

        String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            throw new AuthenticationException("Invalid credentials format");
        }

        return values;
    }

    private boolean isValidCredentials(String username, String password) {
        // Simple validation for demo purposes
        // In production, use proper user authentication service
        return "user".equals(username) && "pwd".equals(password);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("JWT Authentication Service is running!");
    }
}