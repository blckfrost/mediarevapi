package com.frost.mediarevapi.service;

import com.frost.mediarevapi.dto.LoginRequest;
import com.frost.mediarevapi.dto.LoginResponse;
import com.frost.mediarevapi.exception.InvalidCredentialsException;
import com.frost.mediarevapi.model.User;
import com.frost.mediarevapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

}
