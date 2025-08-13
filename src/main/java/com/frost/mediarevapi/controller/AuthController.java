package com.frost.mediarevapi.controller;

import com.frost.mediarevapi.dto.LoginRequest;
import com.frost.mediarevapi.dto.LoginResponse;
import com.frost.mediarevapi.dto.RegisterRequest;
import com.frost.mediarevapi.service.AuthService;
import com.frost.mediarevapi.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest loginRequest,
                                                  HttpServletRequest request) {
        logger.info("Route: {} {}",request.getMethod(), loginRequest);
        LoginResponse respose = authService.login(loginRequest);
        return ResponseEntity.ok(respose);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization Token");
        }

        String token = authHeader.substring(7);
        String username = jwtService.getUsernameFromToken(token);

        return ResponseEntity.ok("User" + username + "logged out successfully");
    }
}
