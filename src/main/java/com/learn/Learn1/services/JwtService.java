package com.learn.Learn1.services;

// service/JwtService.java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        try {
            final String tokenEmail = extractEmail(token);
                System.out.println("[JwtService] Token subject: " + tokenEmail);
                System.out.println("[JwtService] Expected email: " + email);
                boolean expired = isTokenExpired(token);
                System.out.println("[JwtService] Token expired: " + expired);
                boolean valid = (tokenEmail.equals(email) && !expired);
                System.out.println("[JwtService] Token valid: " + valid);
            return (tokenEmail.equals(email) && !isTokenExpired(token));
        } catch (Exception e) {
                System.out.println("[JwtService] Exception in isTokenValid: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
