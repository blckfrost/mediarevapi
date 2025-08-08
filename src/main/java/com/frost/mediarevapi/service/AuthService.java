package com.frost.mediarevapi.service;

import com.frost.mediarevapi.dto.LoginRequest;
import com.frost.mediarevapi.dto.LoginResponse;
import com.frost.mediarevapi.dto.RegisterRequest;
import com.frost.mediarevapi.exception.InvalidCredentialsException;
import com.frost.mediarevapi.model.User;
import com.frost.mediarevapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JWTService jwtService;

    public LoginResponse login(LoginRequest loginRequest) {
        if(loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        // Find user email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Incorrect Email"));


        if(!passwordService.comparePassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        return getLoginResponse(user);
    }

    private LoginResponse getLoginResponse(User user) {
        String token = jwtService.generateAccessToken(
                user.getUsername(),
                user.getEmail(),
                user.getAccountType(),
                user.getUserId(),
                user.getPicturePath()
        );

        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                user.getId(),
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getAccountType(),
                getRoleFromAccountType(user.getAccountType()),
                user.getPicturePath(),
                user.getStatus()
        );
        return new LoginResponse(true, userInfo, token);
    }

    private String getRoleFromAccountType(int accountType) {
        return switch (accountType) {
            case 0 -> "Admin";
            case 1 -> "Data Entry";
            case 2 -> "Tenant";
            case 3 -> "Tenant User";
            default -> "Unknown";
        };
    }

    public LoginResponse register(RegisterRequest request) {
        if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null) {
            throw new InvalidCredentialsException("Missing required fields");
        }

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Email already in use");
        }

        String userId = UUID.randomUUID().toString();

        User user = new User();

        user.setUserId(userId);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordService.hashPassword(request.getPassword()));
        user.setAccountType(request.getAccountType() != null ? Integer.parseInt(request.getAccountType()) : 0);
        user.setPicturePath(request.getPicturePath());
        user.setStatus("active");

        userRepository.save(user);

        return getLoginResponse(user);
    }
}
