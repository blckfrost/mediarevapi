package com.frost.mediarevapi.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret:defaultSecret}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400}")
    private int jwtExpiration;

    public String generateAccessToken(String username, String email, Integer role, String userId,
                                String pictureUrl) {
        Date expiryDate  = new Date(System.currentTimeMillis() + jwtExpiration * 1000);

        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("Role", role)
                .claim("userId", userId)
                .claim("pictureUrl", pictureUrl)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public String getUserIdFromToken(String token){
        return getClaimsFromToken(token).get("userId", String.class);
    }

    public Integer getRoleFromToken(String token) {
        return getClaimsFromToken(token).get("Role", Integer.class);
    }


    public String getJwtSecret(){
        return jwtSecret;
    }

    public int getJwtExpiration(){
        return jwtExpiration;
    }
}
