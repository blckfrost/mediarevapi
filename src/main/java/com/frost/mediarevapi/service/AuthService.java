package com.frost.mediarevapi.service;

import com.frost.mediarevapi.dto.LoginRequest;
import com.frost.mediarevapi.dto.LoginResponse;
import com.frost.mediarevapi.dto.RegisterRequest;
import com.frost.mediarevapi.exception.InvalidCredentialsException;
import com.frost.mediarevapi.model.User;
import com.frost.mediarevapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        Optional<User> userOpt = userRepository.findUserByEmail(loginRequest.getEmail());
        if(userOpt.isEmpty()) {
            throw new InvalidCredentialsException("Incorrect email or password");
        }

        User user = userOpt.get();
        boolean passwordMatch = passwordService.comparePassword(loginRequest.getPassword(), user.getPassword());
        if(!passwordMatch) {
            throw new InvalidCredentialsException("Incorrect email or password");

        }

        return getLoginResponse(user);
    }

    private LoginResponse getLoginResponse(User user) {
        String token = jwtService.generateAccessToken(
                user.getUsername(),
                user.getEmail(),
                user.getAccountType(),
                user.getUid(),
                user.getPicturePath()
        );

        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                user.getUsername(),
                user.getEmail(),
                user.getAccountType(),
                user.getAcType(),
                user.getPicturePath()
        );
        return new LoginResponse(true, userInfo,token);
    }

    public LoginResponse register(RegisterRequest request) {
        if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null) {
            throw new InvalidCredentialsException("Missing required fields");
        }

        if(userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Email already in use");
        }

        String hashedPassword = passwordService.hashPassword(request.getPassword());
        String uid = UUID.randomUUID().toString();

        User user = new User();
        user.setUid(uid);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setAccountType(request.getAccountType() != null ? Integer.parseInt(request.getAccountType()) : 0);
        user.setPicturePath(request.getPicturePath());

        userRepository.save(user);

        return getLoginResponse(user);
    }
}
