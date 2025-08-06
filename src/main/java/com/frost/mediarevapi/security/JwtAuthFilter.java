package com.frost.mediarevapi.security;

import com.frost.mediarevapi.service.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    public JwtAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try{
            Claims claims = jwtService.getClaimsFromToken(token);

            String userId  = claims.get("userId", String.class);
            Integer role = claims.get("Role", Integer.class);

            if(userId == null){
                throw new RuntimeException("Invalid JWT: Missing userId");
            }

            UsernamePasswordAuthenticationToken authToken =
                    new  UsernamePasswordAuthenticationToken(userId, null, null);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("✅ Authenticated userId=" + userId);

            }catch(Exception e){
            System.out.println("❌ JWT Error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
            return; // Stop filter chain
            }

        chain.doFilter(request, response);
    }
}
