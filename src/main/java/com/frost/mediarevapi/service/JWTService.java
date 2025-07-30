package com.frost.mediarevapi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret:defaultSecret}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400}")
    private int jwtExpiration;

    public String generateToken(String username, String email, Integer role, String id,
                                String pictureUrl) {
        Date expiryDate  = new Date(System.currentTimeMillis() + jwtExpiration * 1000);

        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("Role", role)
                .claim("id", id)
                .claim("pictureUrl", pictureUrl)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
